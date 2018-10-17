package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	@Query("select p from Payment p where p.booking.id=:bookingId")
	public List<Payment> getPaymentByBooking(@Param("bookingId") int bookingId);
}
