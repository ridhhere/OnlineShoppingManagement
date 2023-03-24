package com.ridh.service;

import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CheckoutModel;

public interface CheckoutService {
	public CheckoutModel createCheckout(CheckoutModel checkoutModel);

	public CheckoutModel getCheckoutById(Long id) throws RecordNotFoundException;

	public CheckoutModel updateCheckout(Long id, CheckoutModel checkoutModel) throws RecordNotFoundException;

	public String deleteCheckout(Long id) throws RecordNotFoundException;
}
