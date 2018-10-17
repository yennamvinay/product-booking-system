package com.abc.product.bookingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.PendingPayment;

@Repository
public interface PendingPaymentRepository extends CrudRepository<PendingPayment, Integer> {
	@Query("select p from PendingPayment p where p.booking.id=:bookingId")
	public PendingPayment getPaymentByBooking(@Param("bookingId") int bookingId);
	
	@Query("delete from PendingPayment p where p.booking.id=:bookingId")
	public void deleteByBookingId(@Param("bookingId") int bookingId);
}
