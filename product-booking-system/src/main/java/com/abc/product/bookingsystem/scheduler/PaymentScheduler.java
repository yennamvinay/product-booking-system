package com.abc.product.bookingsystem.scheduler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abc.product.bookingsystem.mapper.BookingSystemModelMapper;
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
			pendingPaymentRepository.save(bookingSystemModelMapper.createPendingPaymentFromProcessed(booking));
		}
	}
}
