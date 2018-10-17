package com.abc.product.bookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.abc.product.bookingsystem" })
@EnableScheduling
public class ProductBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductBookingSystemApplication.class, args);
	}
}
