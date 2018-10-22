package com.abc.product.bookingsystem.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.abc.product.bookingsystem.model.Booking;
import com.abc.product.bookingsystem.model.Product;
import com.abc.product.bookingsystem.model.ProductStats;
import com.abc.product.bookingsystem.model.Seller;
import com.abc.product.bookingsystem.model.Stock;
import com.abc.product.bookingsystem.repository.BookingRepository;
import com.abc.product.bookingsystem.repository.ProcessedBookingRepository;
import com.abc.product.bookingsystem.repository.ProductRepository;
import com.abc.product.bookingsystem.repository.SellerRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProcessedBookingRepository processedBookingRepository;

	@Autowired
	private StockService stockService;

	@Autowired
	public JavaMailSender emailSender;

	public Booking create(Booking booking) {
		Product product = productRepository.findById(booking.getProduct().getId()).orElse(null);
		if (product == null) {
			throw new IllegalArgumentException("Invalid Product Details");
		}
		Seller seller = sellerRepository.findById(booking.getSeller().getId()).orElse(null);
		if (seller == null) {
			throw new IllegalArgumentException("Invalid Seller Details");
		}
		Stock stock = stockService.getStockByProductId(booking.getProduct().getId());
		if (booking.getType() == 1 && booking.getCount() > stock.getCount()) {
			// Book on availability type
			throw new IllegalArgumentException("Stock is empty");
		}
		booking.setProduct(product);
		booking.setSeller(seller);
		booking.setBookedDate(new Timestamp(new Date().getTime()));

		Booking savedBooking = bookingRepository.save(booking);
		if (savedBooking != null) {
			if (booking.getType() == 1) {
				stock.setCount(stock.getCount() - savedBooking.getCount());
				stockService.updateStock(stock);
			}
			sendBookingNotification(savedBooking.getProduct().getName(), savedBooking.getSeller().getName());
		}

		return savedBooking;
	}

	private void sendBookingNotification(String productName, String sellerName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("yennamvinay@gmail.com");
		message.setSubject("New Product Booking");
		message.setText("A new booking is done for product " + productName + " by seller " + sellerName);
		emailSender.send(message);
	}

	public void delete(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	public List<ProductStats> getProductsSoldForSellerByYear(int sellerId, int year) {
		return processedBookingRepository.getProductsSoldForSellerByYear(sellerId, year);
	}
}