package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.product.bookingsystem.model.ProcessedBooking;
import com.abc.product.bookingsystem.model.ProductStats;

public interface ProcessedBookingRepository extends JpaRepository<ProcessedBooking, Integer> {
	@Query("select p from ProcessedBooking p where p.paymentStatus=:paymentStatus")
	public List<ProcessedBooking> getBookingByPaymentStatus(@Param("paymentStatus") int paymentStatus);

	@Query(value = "select p.product_id as id, count(*) as count, pro.name as name from processed_booking p inner join product pro where p.seller_id =:sellerId and p.product_id=pro.id and year(p.booked_date) =:startYear group by id", nativeQuery = true)
	public List<ProductStats> getProductsSoldForSellerByYear(@Param("sellerId") int sellerId,
			@Param("startYear") int startYear);
}
