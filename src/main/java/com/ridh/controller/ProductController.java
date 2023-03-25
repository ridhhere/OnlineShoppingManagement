package com.ridh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridh.entity.ProductEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.ProductModel;
import com.ridh.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    @PostMapping("/add")
    public ProductModel createCustomer(@RequestBody ProductModel productModel) {
        return productServiceImpl.createProduct(productModel);
    }
    
    @GetMapping("/get/{id}")
    public ProductModel getProductById(@PathVariable long id) throws RecordNotFoundException {
        return productServiceImpl.getProductById(id);
    }
    
    @PutMapping("/update/{id}")
    public ProductModel updateCustomer(@PathVariable long id, @RequestBody ProductModel productModel) throws RecordNotFoundException {
    	productModel.setProductId(id);
        return productServiceImpl.updateProduct(id,productModel);
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable long id) throws RecordNotFoundException {
    	return productServiceImpl.deleteProduct(id);
    }
    
    @GetMapping("/get")
    public List<ProductModel> getProducts() throws RecordNotFoundException {
        return productServiceImpl.getProducts();
    }
}

