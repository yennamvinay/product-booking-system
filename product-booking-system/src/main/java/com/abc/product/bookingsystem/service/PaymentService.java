package com.abc.product.bookingsystem.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.abc.product.bookingsystem.model.Payment;
import com.abc.product.bookingsystem.model.ProcessedBooking;
import com.abc.product.bookingsystem.repository.PaymentRepository;
import com.abc.product.bookingsystem.repository.PendingPaymentRepository;
import com.abc.product.bookingsystem.repository.ProcessedBookingRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProcessedBookingRepository processedBookingRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	public JavaMailSender emailSender;

	public Payment processPayment(Payment payment) {
		payment.setPaymentDate(new Timestamp(new Date().getTime()));
		Payment dbPayment = paymentRepository.save(payment);
		if (dbPayment != null) {
			try {
				ProcessedBooking booking = processedBookingRepository.findById(dbPayment.getBooking().getId())
						.orElse(null);
				if (booking != null) {
					booking.setPaymentStatus(1);
					processedBookingRepository.save(booking);
					sendPaymentNotificationEmail(dbPayment.getBooking().getProduct().getName(), dbPayment.getAmount(),
							dbPayment.getBooking().getSeller().getName());
				}
			} catch (Exception exp) {
				paymentRepository.deleteById(dbPayment.getId());
			}
			pendingPaymentRepository.deleteByBookingId(payment.getBooking().getId());
		}
		return dbPayment;
	}

	private void sendPaymentNotificationEmail(String productName, double amount, String sellerName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("yennamvinay@gmail.com");
		message.setSubject("Bill Payment");
		message.setText("A new payment has been done for product " + productName + " by seller " + sellerName
				+ " with amount " + amount);
		emailSender.send(message);
	}
}
