package com.abc.product.bookingsystem.mapper;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.abc.product.bookingsystem.model.Booking;
import com.abc.product.bookingsystem.model.PendingPayment;
import com.abc.product.bookingsystem.model.ProcessedBooking;

@Component
public class BookingSystemModelMapper {
	public ProcessedBooking bookingAsProcessed(Booking booking) {
		ProcessedBooking processedBooking = new ProcessedBooking();
		processedBooking.setId(booking.getId());
		processedBooking.setType(booking.getType());
		processedBooking.setProduct(booking.getProduct());
		processedBooking.setSeller(booking.getSeller());
		processedBooking.setCount(booking.getCount());
		processedBooking.setBookedDate(booking.getBookedDate());
		processedBooking.setProcessedDate(new Timestamp(new Date().getTime()));
		processedBooking.setAmount(booking.getCount() * booking.getProduct().getPrice());
		processedBooking.setPaymentStatus(0);
		return processedBooking;
	}

	public PendingPayment createPendingPaymentFromProcessed(ProcessedBooking booking) {
		PendingPayment payment = new PendingPayment();
		payment.setAmount(booking.getAmount());
		payment.setBooking(booking);
		return payment;
	}
}
