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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ApiModelProperty(notes = "This indicates the booking type. 1 indicates book only if stock available and 2 indicates book even if stock is not available.", name = "type", required = true)
	@Column(name = "booking_type")
	private int type;

	@ApiModelProperty(notes = "Id of the product", name = "product", required = true)
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ApiModelProperty(notes = "Id of the Seller", name = "seller", required = true)
	@OneToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;

	@ApiModelProperty(notes = "Count of the products required.", name = "count", required = true)
	private int count;

	@ApiModelProperty(notes = "Name of the Seller", name = "bookedDate", required = false)
	@Column(name = "booked_date")
	private Timestamp bookedDate;
}
