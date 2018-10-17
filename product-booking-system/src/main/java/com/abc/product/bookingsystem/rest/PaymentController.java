package com.abc.product.bookingsystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.product.bookingsystem.model.Payment;
import com.abc.product.bookingsystem.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/pay")
	public Payment doPayment(@RequestBody Payment payment) {
		return paymentService.processPayment(payment);
	}
}
