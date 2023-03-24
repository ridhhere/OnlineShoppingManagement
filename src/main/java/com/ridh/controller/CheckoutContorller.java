package com.ridh.controller;

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
import com.ridh.model.CheckoutModel;
import com.ridh.service.impl.CheckoutServiceImpl;

@RestController
@RequestMapping("/checkout")
public class CheckoutContorller {
    @Autowired
    private CheckoutServiceImpl checkoutServiceImpl;
    
    @PostMapping("/add")
    public CheckoutModel createCheckout(@RequestBody CheckoutModel checkoutModel) {
        return checkoutServiceImpl.createCheckout(checkoutModel);
    }
    
    @GetMapping("/get/{id}")
    public CheckoutModel getCheckoutById(@PathVariable long id) throws RecordNotFoundException {
        return checkoutServiceImpl.getCheckoutById(id);
    }
    
    @PutMapping("/update/{id}")
    public CheckoutModel updateCheckout(@PathVariable long id, @RequestBody CheckoutModel checkoutModel) throws RecordNotFoundException {
    	checkoutModel.setCheckoutId(id);
        return checkoutServiceImpl.updateCheckout(id,checkoutModel);
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteCheckout(@PathVariable long id) throws RecordNotFoundException {
    	return checkoutServiceImpl.deleteCheckout(id);
    }
}
