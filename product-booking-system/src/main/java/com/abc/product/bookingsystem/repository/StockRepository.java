package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.abc.product.bookingsystem.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
	@Query("select s from Stock s where s.product.id=:productId")
	public List<Stock> getStocksByProductId(@Param("productId") int productId);
}
