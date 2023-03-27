package com.ridh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridh.entity.CheckoutEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CheckoutModel;
import com.ridh.repo.CheckoutRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.service.impl.CheckoutServiceImpl;

@RestController
@RequestMapping("/checkout")
public class CheckoutContorller {
	@Autowired
	private CheckoutServiceImpl checkoutServiceImpl;
	
	@Autowired
	private CheckoutRepo checkoutRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/{customerId}")
	public ResponseEntity<CheckoutEntity> checkoutCart(@PathVariable Long customerId) {
		try {
			CheckoutEntity checkoutItem = checkoutServiceImpl.checkoutCart(customerId);
			return ResponseEntity.ok(checkoutItem);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

//	@GetMapping("/customers/{customerId}/checkouts")
//	public List<CheckoutEntity> getAllCheckouts(@PathVariable Long customerId) {
//		CustomerEntity customer = customerRepo.findById(customerId)
//				.orElseThrow(() -> new RecordNotFoundException("Customer not found with id " + customerId));
//		return checkoutRepo.findByCustomer(customer);
//	}
}
