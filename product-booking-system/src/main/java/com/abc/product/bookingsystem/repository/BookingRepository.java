package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
	@Query("select b from Booking b where b.type=:type")
	public List<Booking> getByType(@Param("type") int type);
}
