package com.ridh.service;

import java.util.List;

import com.ridh.entity.ProductEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.ProductModel;

public interface ProductService {
	public ProductModel createProduct(ProductModel productModel);

	public ProductModel getProductById(Long id) throws RecordNotFoundException;

	public ProductModel updateProduct(Long id, ProductModel productModel) throws RecordNotFoundException;

	public String deleteProduct(Long id) throws RecordNotFoundException;

	public List<ProductModel> getProducts() throws RecordNotFoundException;
}
