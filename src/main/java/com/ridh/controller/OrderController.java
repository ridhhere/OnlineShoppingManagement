package com.ridh.controller;

import com.ridh.DTO.CustomerDTO;
import com.ridh.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ridh.entity.OrderEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.repo.OrderRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/{customerId}")
	public ResponseEntity<OrderEntity> checkoutCart(@PathVariable Long customerId) {
		try {
			OrderEntity checkoutItem = orderServiceImpl.checkoutCart(customerId);
			return ResponseEntity.ok(checkoutItem);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<CustomerDTO> getItemsByStatus(@PathVariable Long customerId, @RequestParam(name = "status", required = false) StatusEnum status) {
		try {
			CustomerDTO customerDTO;

			if (status != null) {
				// If 'status' parameter is provided, filter by status
				customerDTO = orderServiceImpl.getItemsByStatus(customerId, status);
			} else {
				// If 'status' parameter is not provided, retrieve all items
				customerDTO = orderServiceImpl.getAllItems(customerId);
			}

			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
