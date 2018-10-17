package com.abc.product.bookingsystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "seller")
@Data
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ApiModelProperty(notes = "Name of the Seller", name = "name", required = true)
	private String name;

	@ApiModelProperty(notes = "Address of the Seller", name = "address", required = true)
	private String address;
}
