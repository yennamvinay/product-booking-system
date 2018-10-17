package com.abc.product.bookingsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.product.bookingsystem.model.Booking;
import com.abc.product.bookingsystem.model.Stock;
import com.abc.product.bookingsystem.service.StockService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("stock")
public class StockController {
	@Autowired
	private StockService stockService;

	/**
	 * Will fetch the stock information for all the products.
	 * 
	 * @return the list of stock objects.
	 */
	@ApiOperation(value = "Get Stocks", notes = "This API would fetch the stocks information for all the prodcuts.", response = Stock.class)
	@GetMapping("/all")
	public List<Stock> getStocks() {
		return stockService.getStocks();
	}

	/**
	 * This method will get stock details by product id.
	 * 
	 * @param id
	 *            the product id.
	 * @return the stock object.
	 */
	@ApiOperation(value = "Get Stock", notes = "This API would fetch the stock information by product id.", response = Stock.class)
	@GetMapping("/{id}")
	public Stock getProductStock(@PathVariable("id") int id) {
		return stockService.getStockByProductId(id);
	}

	/**
	 * This method will add stock details.
	 * 
	 * @param stock
	 *            the stock object
	 * @return the added stock object.
	 */
	@ApiOperation(value = "Add Stock", notes = "This API would add stock for the products.", response = Stock.class)
	@PostMapping("/add")
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}
}
