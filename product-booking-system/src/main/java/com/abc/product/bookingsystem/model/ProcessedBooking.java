package com.abc.product.bookingsystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "processed_booking")
@Data
public class ProcessedBooking {
	@Id
	private int id;

	@Column(name = "booking_type")
	private int type;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;

	private int count;

	private double amount;

	@Column(name = "payment_status")
	private int paymentStatus;// 0-Not Paid, 1=Paid

	@Column(name = "booked_date")
	private Timestamp bookedDate;

	@Column(name = "processed_date")
	private Timestamp processedDate;
}
