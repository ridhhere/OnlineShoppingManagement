package com.ridh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.DTO.CartDTO;
import com.ridh.DTO.CustomerDTO;
import com.ridh.DTO.ProductDTO;
import com.ridh.entity.CartEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;
import com.ridh.enums.StatusEnum;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.repo.CartRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.repo.ProductRepo;

@Service
public class ShoppingCartService {
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CustomerRepo customerRepo;

	public CartDTO addCart(Long customerId, Long productId, Long quantity) throws RecordNotFoundException {
	    // Retrieve the customer and product entities
	    CustomerEntity customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RecordNotFoundException("Customer with id " + customerId + " not found"));
	    ProductEntity product = productRepo.findById(productId)
	            .orElseThrow(() -> new RecordNotFoundException("Product with id " + productId + " not found"));

	    // Create a new cart item
	    CartEntity cartItem = new CartEntity();
	    cartItem.setCustomer(customer);
	    cartItem.setProduct(product);
	    cartItem.setQuantity(quantity);
	    cartItem.setStatus(StatusEnum.IN_CART);
	    cartItem.setTotalPrice(product.getPrice() * quantity);
	    cartRepo.save(cartItem);

	    // Convert the cart item entity to a DTO and return it
	    return modelMapper.map(cartItem, CartDTO.class);
	}




	public String removeItem(Long cartItemId) throws RecordNotFoundException {
        // Retrieve the cart item entity
        CartEntity cartItem = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new RecordNotFoundException("Cart item with id " + cartItemId + " not found"));
		// Check if the cart item's status is IN_CART
		if (cartItem.getStatus() == StatusEnum.IN_CART) {
			// Remove the cart item if the status is IN_CART
			cartRepo.delete(cartItem);
			return "Deleted Successfully!!";
		} else {
			// Handle the case where the status is not IN_CART
			return "Item cannot be deleted because it is not in the IN_CART status.";
		}
    }

}
