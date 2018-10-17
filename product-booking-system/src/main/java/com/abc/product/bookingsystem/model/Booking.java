package com.abc.product.bookingsystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "booking")
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "booked_date")
	private Timestamp bookedDate;
}
