package com.ridh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ridh.DTO.CartDTO;
import com.ridh.DTO.CustomerDTO;
import com.ridh.DTO.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CartEntity;
import com.ridh.entity.OrderEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;
import com.ridh.enums.StatusEnum;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.repo.CartRepo;
import com.ridh.repo.OrderRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.repo.ProductRepo;
import com.ridh.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ProductRepo productRepo;

	@Override
	public OrderEntity checkoutCart(Long customerId) throws RecordNotFoundException {
	    CustomerEntity customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RecordNotFoundException("Customer not found with id " + customerId));

	    List<CartEntity> cartItems = cartRepo.findByCustomer(customer).stream()
				.filter(cart -> cart.getStatus().equals(StatusEnum.IN_CART)).collect(Collectors.toList());
	    if (cartItems.isEmpty()) {
	        throw new RecordNotFoundException("No items found in cart for customer with id " + customerId);
	    }

	    double totalPrice = 0.0;
	    List<ProductEntity> productsToUpdate = new ArrayList<>();
	    for (CartEntity cartItem : cartItems) {
	        ProductEntity product = cartItem.getProduct();
	        long quantity = cartItem.getQuantity();
	        long availableQuantity = product.getAvailableQuantity();
	        if (quantity > availableQuantity) {
	            throw new RuntimeException("Insufficient quantity for product with id " + product.getProductId());
	        }
	        product.setAvailableQuantity(availableQuantity - quantity);
	        productsToUpdate.add(product);
	        totalPrice += cartItem.getTotalPrice();
	        cartItem.setStatus(StatusEnum.ORDERED);
	        cartRepo.save(cartItem);
	    }

	    OrderEntity checkoutItem = new OrderEntity();
//	    checkoutItem.setCustomer(customer);
	    checkoutItem.setPaymentInformation(customer.getPaymentInformation());
	    checkoutItem.setShippingAddress(customer.getShippingAddress());
	    checkoutItem.setBillingAddress(customer.getBillingAddress());
	    checkoutItem.setTotalPrice(totalPrice);
	    checkoutItem.setOrderStatus(StatusEnum.ORDERED);
	    orderRepo.save(checkoutItem);

	    for (ProductEntity product : productsToUpdate) {
	        productRepo.save(product);
	    }

	    return checkoutItem;
	}


	public CustomerDTO getItemsByStatus(Long customerId, StatusEnum status) throws RecordNotFoundException {
		// Retrieve the customer entity
		CustomerEntity customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RecordNotFoundException("Customer with id " + customerId + " not found"));

		// Convert customer entity to DTO
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

		// Retrieve all cart items for the customer with the specified status and convert them to DTOs
		List<CartDTO> cart = new ArrayList<>();
		for (CartEntity cartItem : cartRepo.findByCustomerAndStatus(customer, status)) {
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


	public CustomerDTO getAllItems(Long customerId) throws RecordNotFoundException {
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
