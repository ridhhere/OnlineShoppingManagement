package com.ridh.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CustomerModel;
import com.ridh.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	  private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    
    @PostMapping("/add")
    public CustomerModel createCustomer(@RequestBody CustomerModel customerModel) {
        return customerServiceImpl.createCustomer(customerModel);
    }
    
    @GetMapping("/get/{id}")
    public CustomerModel getCustomerById(@PathVariable long id) throws RecordNotFoundException {
        logger.info("CustomerController:getCustomerById execution started !!");
    	return customerServiceImpl.getCustomerById(id);
    }
    
    @PutMapping("/update/{id}")
    public CustomerModel updateCustomer(@PathVariable long id, @RequestBody CustomerModel customerModel) throws RecordNotFoundException {
    	customerModel.setCustomerId(id);
        return customerServiceImpl.updateCustomer(id,customerModel);
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteCustomer(@PathVariable long id) throws RecordNotFoundException {
    	return customerServiceImpl.deleteCustomer(id);
    }
}
