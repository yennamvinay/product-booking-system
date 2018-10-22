package com.abc.product.bookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.product.bookingsystem.model.Product;
import com.abc.product.bookingsystem.model.Stock;
import com.abc.product.bookingsystem.repository.ProductRepository;
import com.abc.product.bookingsystem.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(stock -> stocks.add(stock));
		return stocks;
	}

	public Stock getStockByProductId(int productId) {
		return stockRepository.findById(productId).get();
	}

	public Stock addStock(Stock stock) {
		List<Stock> stocks = stockRepository.getStocksByProductId(stock.getProduct().getId());
		Stock dbStock = null;
		if (stocks != null && !stocks.isEmpty()) {
			dbStock = stocks.get(0);
		}
		if (dbStock != null) {
			dbStock.setCount(dbStock.getCount() + stock.getCount());
		} else {
			Product product = productRepository.findById(stock.getProduct().getId()).get();
			stock.setProduct(product);
			dbStock = stock;
		}
		return stockRepository.save(dbStock);
	}

	public void updateStock(Stock stock) {
		stockRepository.save(stock);
	}
}
