package com.abc.product.bookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.product.bookingsystem.model.Seller;
import com.abc.product.bookingsystem.repository.SellerRepository;

@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepository;

	public Seller saveOrUpdate(Seller seller) {
		return sellerRepository.save(seller);
	}

	public void delete(int id) {
		sellerRepository.deleteById(id);
	}

	public List<Seller> getSellers() {
		List<Seller> sellers = new ArrayList<>();
		sellerRepository.findAll().forEach(product -> sellers.add(product));
		return sellers;
	}

	public Seller getById(int id) {
		return sellerRepository.findById(id).get();
	}
}
