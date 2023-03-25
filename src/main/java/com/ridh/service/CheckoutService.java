package com.ridh.service;

import com.ridh.entity.CheckoutEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CheckoutModel;

public interface CheckoutService {
	public CheckoutEntity checkoutCart(Long customerId) throws RecordNotFoundException;
}
