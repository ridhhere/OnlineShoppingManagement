package com.ridh.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.DTO.CartDTO;
import com.ridh.DTO.CustomerDTO;
import com.ridh.DTO.ProductDTO;
import com.ridh.entity.CartEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;
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
	    cartItem.setTotalPrice(product.getPrice() * quantity);
	    cartRepo.save(cartItem);

	    // Convert the cart item entity to a DTO and return it
	    return modelMapper.map(cartItem, CartDTO.class);
	}
	

	
	
	public String removeItem(Long cartItemId) throws RecordNotFoundException {
        // Retrieve the cart item entity
        CartEntity cartItem = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new RecordNotFoundException("Cart item with id " + cartItemId + " not found"));

        // Remove the cart item
        cartRepo.delete(cartItem);
        return "Deleted Succesfully!!";
    }
	
	public CustomerDTO getCustomer(Long customerId) throws RecordNotFoundException {
	    // Retrieve the customer entity
	    CustomerEntity customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RecordNotFoundException("Customer with id " + customerId + " not found"));

	    // Convert customer entity to DTO
	    CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

	    // Retrieve all cart items for the customer and convert them to DTOs
	    List<CartDTO> cart = new ArrayList<>();
	    for (CartEntity cartItem : cartRepo.findByCustomer(customer)) {
	        // Convert cart item entity to DTO
	        CartDTO cartItemDTO = modelMapper.map(cartItem, CartDTO.class);

	        // Retrieve the product for the cart item and convert it to a DTO
	        ProductEntity productEntity = cartItem.getProduct();
	        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);

	        // Set the product for the cart item DTO
	        cartItemDTO.setProductDetails(productDTO);

	        // Add the cart item DTO to the list of cart items
	        cart.add(cartItemDTO);
	    }

	    // Set the cart items for the customer DTO
	    customerDTO.setCartItems(cart);

	    return customerDTO;
	}



}
