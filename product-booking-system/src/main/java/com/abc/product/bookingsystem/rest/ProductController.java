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

import com.abc.product.bookingsystem.model.Product;
import com.abc.product.bookingsystem.model.ReplenishedProduct;
import com.abc.product.bookingsystem.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
	 * This method will create Product information.
	 * 
	 * @param product
	 *            the Product object.
	 * @return the saved Product object.
	 */
	@ApiOperation(value = "Create Product", notes = "This API would create Product information.", response = Product.class)
	@PostMapping("/create")
	public Product create(@RequestBody Product product) {
		return productService.saveOrUpdate(product);
	}

	/**
	 * This method will update the Product information.
	 * 
	 * @param product
	 *            the Product object.
	 * @param id
	 *            the Product id.
	 * @return the updated Product object.
	 */
	@ApiOperation(value = "Update Product", notes = "This API would update Product information.", response = Product.class)
	@PostMapping("/update/{id}")
	public Product create(@RequestBody Product product, @PathVariable("id") int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		product.setId(id);
		return productService.saveOrUpdate(product);
	}

	/**
	 * This method will delete the Product information by ID.
	 * 
	 * @param id
	 *            the Product id.
	 */
	@ApiOperation(value = "Delete Product", notes = "This API would delete Product information.", response = Void.class)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		if (id == 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		productService.delete(id);
	}

	/**
	 * This method will fetch all Product information.
	 * 
	 * @return the list of Product objects
	 */
	@ApiOperation(value = "Fetch All Product Details", notes = "This API would fetch all Product information.", response = Product.class)
	@GetMapping("/all")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	/**
	 * This method will fetch the product information by Id.
	 * 
	 * @param id
	 *            the product id.
	 * @return the matched product object.
	 */
	@ApiOperation(value = "Fetch Product Details", notes = "This API would fetch Product information by id.", response = Product.class)
	@GetMapping("/{id}")
	public Product getById(@PathVariable("id") int id) {
		return productService.getById(id);
	}
	
	@GetMapping("/replenished")
	public List<ReplenishedProduct> getReplenishedProducts(){
		return productService.getReplenishedProducts();
	}
}
