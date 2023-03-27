package com.ridh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CartEntity;
import com.ridh.entity.CheckoutEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;
import com.ridh.enums.StatusEnum;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CheckoutModel;
import com.ridh.repo.CartRepo;
import com.ridh.repo.CheckoutRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.repo.ProductRepo;
import com.ridh.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	@Autowired
	private CheckoutRepo checkoutRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ProductRepo productRepo;

	@Override
	public CheckoutEntity checkoutCart(Long customerId) throws RecordNotFoundException {
	    CustomerEntity customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RecordNotFoundException("Customer not found with id " + customerId));

	    List<CartEntity> cartItems = cartRepo.findByCustomer(customer);
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

	    CheckoutEntity checkoutItem = new CheckoutEntity();
//	    checkoutItem.setCustomer(customer);
	    checkoutItem.setPaymentInformation(customer.getPaymentInformation());
	    checkoutItem.setShippingAddress(customer.getShippingAddress());
	    checkoutItem.setBillingAddress(customer.getBillingAddress());
	    checkoutItem.setTotalPrice(totalPrice);
	    checkoutItem.setOrderStatus(StatusEnum.ORDERED);
	    checkoutRepo.save(checkoutItem);

	    for (ProductEntity product : productsToUpdate) {
	        productRepo.save(product);
	    }

	    return checkoutItem;
	}



}
