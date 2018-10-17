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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@ApiOperation(value = "Create Seller", notes = "This API would create Seller information.", response = Seller.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "") })
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
	@ApiOperation(value = "Update Seller", notes = "This API would update Seller information.", response = Seller.class)
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
	@ApiOperation(value = "Delete Seller", notes = "This API would delete Seller information.", response = Void.class)
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
	@ApiOperation(value = "Fetch All Seller Details", notes = "This API would fetch all Sellers information.", response = Seller.class)
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
	@ApiOperation(value = "Fetch Seller Details", notes = "This API would fetch Seller information by id.", response = Seller.class)
	@GetMapping("/{id}")
	public Seller getById(@PathVariable("id") int id) {
		return sellerService.getById(id);
	}
}
