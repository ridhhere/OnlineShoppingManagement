package com.ridh.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridh.exception.RecordNotFoundException;
import com.ridh.repo.CartRepo;
import com.ridh.repo.CheckoutRepo;
import com.ridh.repo.CustomerRepo;
import com.ridh.repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cleanup")
@Slf4j
public class CommonController {
	private CheckoutRepo checkoutRepo;
	private CartRepo cartRepo;
	private CustomerRepo customerRepo;
	private ProductRepo productRepo;

	@DeleteMapping("/delete")
	public String deleteCheckout() throws RecordNotFoundException {
	    String msg="";
	    try {
	        checkoutRepo.deleteAllInBatch();
	    } catch (Exception e) {
	        log.error("Error deleting records from CheckoutRepo: " + e.getMessage());
	    }

	    try {
	        cartRepo.deleteAllInBatch();
	    } catch (Exception e) {
	        log.error("Error deleting records from CartRepo: " + e.getMessage());
	    }

	    try {
	        customerRepo.deleteAllInBatch();
	    } catch (Exception e) {
	        log.error("Error deleting records from CustomerRepo: " + e.getMessage());
	    }

	    try {
	        productRepo.deleteAllInBatch();
	    } catch (Exception e) {
	        log.error("Error deleting records from ProductRepo: " + e.getMessage());
	    }

	    msg="All Records Deleted Successfully!!";
	    return msg;
	}

}
