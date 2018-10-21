package com.abc.product.bookingsystem.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
				}
			} catch (Exception exp) {
				paymentRepository.deleteById(dbPayment.getId());
			}
			pendingPaymentRepository.deleteByBookingId(payment.getBooking().getId());
		}
		return dbPayment;
	}
}
