package com.abc.product.bookingsystem.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abc.product.bookingsystem.mapper.BookingSystemModelMapper;
import com.abc.product.bookingsystem.model.Booking;
import com.abc.product.bookingsystem.model.ProcessedBooking;
import com.abc.product.bookingsystem.model.Stock;
import com.abc.product.bookingsystem.repository.BookingRepository;
import com.abc.product.bookingsystem.repository.ProcessedBookingRepository;
import com.abc.product.bookingsystem.repository.StockRepository;

@Component
public class BookingScheduler {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ProcessedBookingRepository processedBookingRepository;

	@Autowired
	private BookingSystemModelMapper mapper;

	@Autowired
	private StockRepository stockRepository;

	@Scheduled(cron = "0 0 0 1/1 * ?")
	public void schedule() {
		List<Booking> bookOnlyAvailableList = bookingRepository.getByType(1);
		processBookings(bookOnlyAvailableList);
		List<Booking> bookOnAvaiableList = bookingRepository.getByType(2);
		processBookings(bookOnAvaiableList);
	}

	private void processBookings(List<Booking> bookings) {
		if (bookings != null && !bookings.isEmpty()) {
			bookings.forEach(booking -> {
				processBooking(booking);
			});
		}
	}

	private void processBooking(Booking booking) {
		List<Stock> stocks = stockRepository.getStocksByProductId(booking.getProduct().getId());
		Stock stock = null;
		if (!stocks.isEmpty()) {
			stock = stocks.get(0);
		}
		if (stock != null && stock.getCount() >= booking.getCount()) {
			ProcessedBooking processed = mapper.bookingAsProcessed(booking);
			processed = processedBookingRepository.save(processed);
			try {
				bookingRepository.deleteById(processed.getId());
			} catch (Exception exp) {
				processedBookingRepository.deleteById(processed.getId());
				return;
			}
			try {
				stock.setCount(stock.getCount() - booking.getCount());
				stockRepository.save(stock);
			} catch (Exception exp) {
				processedBookingRepository.deleteById(processed.getId());
				bookingRepository.deleteById(processed.getId());
			}
		}
	}
}
