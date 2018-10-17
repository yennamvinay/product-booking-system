package com.abc.product.bookingsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.product.bookingsystem.model.Booking;
import com.abc.product.bookingsystem.model.Payment;
import com.abc.product.bookingsystem.service.BookingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	/**
	 * This method will add booking information.
	 * 
	 * @param booking
	 *            the booking object
	 * @return the saved booking object
	 */
	@ApiOperation(value = "Book Order", notes = "This API would take booking order for the products.", response = Booking.class)
	@PostMapping("/add")
	public Booking addBooking(@RequestBody Booking booking) {
		return bookingService.create(booking);
	}

	/**
	 * This method will delete the booking by id.
	 * 
	 * @param bookingId
	 *            the booking id.
	 */
	@ApiOperation(value = "Cancel booking order", notes = "This API would cancel booking order for the products.", response = Booking.class)
	@DeleteMapping("/{bookingId}")
	public void cancelBooking(@PathVariable("bookingId") int bookingId) {
		bookingService.delete(bookingId);
	}
}
