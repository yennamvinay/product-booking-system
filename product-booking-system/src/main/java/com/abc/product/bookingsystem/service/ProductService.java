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

	public Product saveOrUpdate(Product product) {
		return productRepository.save(product);
	}

	public void delete(int id) {
		productRepository.deleteById(id);
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}

	public Product getById(int id) {
		return productRepository.findById(id).get();
	}

	public List<ReplenishedProduct> getReplenishedProducts() {
		return productRepository.getReplinishedProductStats();
	}
}
