package com.ridh.service;

import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CustomerModel;

import java.util.List;

public interface CustomerService {
	public CustomerModel createCustomer(CustomerModel customerModel);
    
    public CustomerModel getCustomerById(Long id) throws RecordNotFoundException;
    
    public CustomerModel updateCustomer(Long id,CustomerModel customerModel) throws RecordNotFoundException;
    
    public String deleteCustomer(Long id) throws RecordNotFoundException;

    public void updateStatusIfInactive();

    List<CustomerModel> getAllCustomer() throws RecordNotFoundException;
}
