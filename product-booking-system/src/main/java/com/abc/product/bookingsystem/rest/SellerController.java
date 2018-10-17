package com.abc.product.bookingsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.product.bookingsystem.model.Seller;
import com.abc.product.bookingsystem.service.SellerService;

@RestController
@RequestMapping("seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;

	/**
	 * This method will create seller information.
	 * 
	 * @param seller
	 *            the seller object.
	 * @return the saved seller object.
	 */
	@PostMapping("/create")
	public Seller create(@RequestBody Seller seller) {
		return sellerService.saveOrUpdate(seller);
	}

	/**
	 * This method will update the seller information.
	 * 
	 * @param seller
	 *            the seller object.
	 * @param id
	 *            the seller id.
	 * @return the updated seller object.
	 */
	@PostMapping("/update/{id}")
	public Seller create(@RequestBody Seller seller, @PathVariable("id") int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		seller.setId(id);
		return sellerService.saveOrUpdate(seller);
	}

	/**
	 * This method will delete the seller information by ID.
	 * 
	 * @param id
	 *            the seller id.
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		if (id == 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		sellerService.delete(id);
	}

	/**
	 * This method will fetch all seller information.
	 * 
	 * @return the list of seller objects
	 */
	@GetMapping("/all")
	public List<Seller> getSellers() {
		return sellerService.getSellers();
	}

	/**
	 * This method will fetch seller information by ID.
	 * 
	 * @param id
	 *            the seller id
	 * @return matched Seller object
	 */
	@GetMapping("/{id}")
	public Seller getById(@PathVariable("id") int id) {
		return sellerService.getById(id);
	}
}
