package com.abc.product.bookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.product.bookingsystem.model.Product;
import com.abc.product.bookingsystem.model.ReplenishedProduct;
import com.abc.product.bookingsystem.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Will save or update a product details
	 * 
	 * @param product
	 *            the product object details.
	 * @return the saved product details.
	 */
	public Product saveOrUpdate(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Will delete product details by id.
	 * 
	 * @param id
	 *            the product id.
	 */
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	/**
	 * Will fetch the list of product details.
	 * 
	 * @return the list of product details.
	 */
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}

	/**
	 * Will fetch product details by id.
	 * 
	 * @param id
	 *            the product id.
	 * @return the matched product details.
	 */
	public Product getById(int id) {
		return productRepository.findById(id).get();
	}

	public List<ReplenishedProduct> getReplenishedProducts() {
		return productRepository.getReplinishedProductStats();
	}
}
