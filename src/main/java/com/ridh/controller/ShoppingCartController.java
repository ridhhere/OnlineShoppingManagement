package com.ridh.controller;

import java.util.List;

import com.ridh.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	
	@DeleteMapping("/delete/{cartId}")
    public String removeItem(@PathVariable Long cartId) throws RecordNotFoundException {
		return cartService.removeItem(cartId);
    }



}
