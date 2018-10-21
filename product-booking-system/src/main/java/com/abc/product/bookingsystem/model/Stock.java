package com.abc.product.bookingsystem.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "stock")
@Data
public class Stock implements Serializable {
	private static final long serialVersionUID = 5299495870239862612L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ApiModelProperty(notes = "Products Details", name = "product", required = true)
	@JoinColumn(name = "product_id", unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;

	@ApiModelProperty(notes = "Count of the products that is available", name = "count", required = true)
	private int count;
}
