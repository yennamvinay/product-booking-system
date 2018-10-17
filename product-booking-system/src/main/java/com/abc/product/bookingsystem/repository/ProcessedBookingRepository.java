package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.ProcessedBooking;

@Repository
public interface ProcessedBookingRepository extends JpaRepository<ProcessedBooking, Integer> {
	@Query("select p from ProcessedBooking p where p.paymentStatus=:paymentStatus")
	public List<ProcessedBooking> getBookingByPaymentStatus(@Param("paymentStatus") int paymentStatus);
}
