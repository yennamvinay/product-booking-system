package com.abc.product.bookingsystem.model;

import java.io.Serializable;
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
@Table(name = "payment")
@Data
public class Payment implements Serializable {
	private static final long serialVersionUID = 2943260994404559276L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	private double amount;

	@Column(name = "payment_date")
	private Timestamp paymentDate;
}
