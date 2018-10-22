package com.abc.product.bookingsystem.scheduler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abc.product.bookingsystem.mapper.BookingSystemModelMapper;
import com.abc.product.bookingsystem.model.PendingPayment;
import com.abc.product.bookingsystem.model.ProcessedBooking;
import com.abc.product.bookingsystem.repository.PendingPaymentRepository;
import com.abc.product.bookingsystem.repository.ProcessedBookingRepository;

@Component
public class PaymentScheduler {
	@Autowired
	private ProcessedBookingRepository processedBookingRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	private BookingSystemModelMapper bookingSystemModelMapper;

	@Autowired
	public JavaMailSender emailSender;

	@Scheduled(cron = "0 0 0 1 1/1 ?")
	public void schedule() {
		List<ProcessedBooking> bookings = processedBookingRepository.getBookingByPaymentStatus(0);
		if (bookings != null && !bookings.isEmpty()) {
			bookings.forEach(booking -> {
				processBookingPaymentStatus(booking);
			});
		}
	}

	private void processBookingPaymentStatus(ProcessedBooking booking) {
		LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(booking.getProcessedDate().getTime()),
				ZoneId.systemDefault());
		LocalDateTime now = LocalDateTime.now();
		now = now.minusMonths(1);
		if (now.getYear() >= date.getYear() && now.getMonth().getValue() >= date.getMonth().getValue()) {
			PendingPayment pendingPayment = bookingSystemModelMapper.createPendingPaymentFromProcessed(booking);
			pendingPaymentRepository.save(pendingPayment);
			sendPendingPaymentNotification(pendingPayment.getBooking().getProduct().getName(),
					pendingPayment.getAmount(), pendingPayment.getBooking().getSeller().getName());
		}
	}

	private void sendPendingPaymentNotification(String productName, double amount, String sellerName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("yennamvinay@gmail.com");
		message.setSubject("Payment Pending by " + sellerName);
		message.setText("An amount of RS " + amount + " is pending for payment by " + sellerName + " for product "
				+ productName);
		emailSender.send(message);

	}
}
