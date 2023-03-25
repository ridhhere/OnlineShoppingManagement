package com.ridh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridh.DTO.CartDTO;
import com.ridh.DTO.CustomerDTO;
import com.ridh.entity.CartEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.service.ShoppingCartService;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService cartService;

	@PostMapping("/add/customer/{customerId}/product/{productId}/quantity/{quantity}")
	public ResponseEntity<CartDTO> addCartItem(@PathVariable Long customerId, @PathVariable Long productId,
			@PathVariable Long quantity) throws RecordNotFoundException {
		CartDTO cartDTO = cartService.addCart(customerId, productId, quantity);
		return ResponseEntity.ok(cartDTO);
	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customerId) {
		try {
			CustomerDTO customerDTO = cartService.getCustomer(customerId);
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{cartId}")
    public String removeItem(@PathVariable Long cartId) throws RecordNotFoundException {
		return cartService.removeItem(cartId);
    }

}
