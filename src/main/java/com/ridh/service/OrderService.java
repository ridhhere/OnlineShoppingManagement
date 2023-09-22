package com.ridh.service;

import com.ridh.entity.OrderEntity;
import com.ridh.exception.RecordNotFoundException;

public interface OrderService {
	public OrderEntity checkoutCart(Long customerId) throws RecordNotFoundException;
}
