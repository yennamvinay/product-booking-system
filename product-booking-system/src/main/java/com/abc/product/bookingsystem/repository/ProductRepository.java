package com.abc.product.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abc.product.bookingsystem.model.Product;
import com.abc.product.bookingsystem.model.ReplenishedProduct;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	@Query(value = "select pro.name as name , pro.id as id, (select sum(b.count) from booking b where b.product_id=s.product_id) - s.count as count from stock s, product pro where s.product_id = pro.id", nativeQuery = true)
	public List<ReplenishedProduct> getReplinishedProductStats();
}
