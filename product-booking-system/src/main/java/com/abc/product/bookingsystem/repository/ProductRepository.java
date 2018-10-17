package com.abc.product.bookingsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
