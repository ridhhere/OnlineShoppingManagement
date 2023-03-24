package com.ridh.service;

import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.ProductModel;

public interface ProductService {
	public ProductModel createProduct(ProductModel productModel);

	public ProductModel getProductById(Long id) throws RecordNotFoundException;

	public ProductModel updateProduct(Long id, ProductModel productModel) throws RecordNotFoundException;

	public String deleteProduct(Long id) throws RecordNotFoundException;
}
