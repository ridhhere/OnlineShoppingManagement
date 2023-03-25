package com.ridh.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.ProductEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.ProductModel;
import com.ridh.repo.ProductRepo;
import com.ridh.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ProductRepo daoImpl;

	@Override
	public ProductModel createProduct(ProductModel productModel) {
		// TODO Auto-generated method stub
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productModel, productEntity);
		ProductEntity saveEntity = daoImpl.save(productEntity);
		ProductModel newCustomerModel = new ProductModel();
		BeanUtils.copyProperties(saveEntity, newCustomerModel);
		return newCustomerModel;
	}

	@Override
	public ProductModel getProductById(Long id) throws RecordNotFoundException {
		Optional<ProductEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			ProductEntity customerEntity = findById.get();
			ProductModel newCustomerModel = new ProductModel();
			BeanUtils.copyProperties(customerEntity, newCustomerModel);
			return newCustomerModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public ProductModel updateProduct(Long id, ProductModel customerModel) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<ProductEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			ProductEntity customerEntity = findById.get();
			customerModel.setProductId(id);
			BeanUtils.copyProperties(customerModel, customerEntity);
			ProductEntity saveEntity = daoImpl.save(customerEntity);
			ProductModel newCustomerModel = new ProductModel();
			modelMapper.map(saveEntity, newCustomerModel);
//			BeanUtils.copyProperties(saveEntity, newCustomerModel);
			return newCustomerModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public String deleteProduct(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<ProductEntity> findById = daoImpl.findById(id);
		String msg="";
		if (findById.isPresent()) {
			daoImpl.deleteById(id);
			msg="Deleted Succesfully!!!";
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
		return msg;

	}
	
	@Override
	public List<ProductModel> getProducts() throws RecordNotFoundException {
        List<ProductEntity> products = daoImpl.findAll();
        if (products.isEmpty()) {
            throw new RecordNotFoundException("No products found");
        }
        return products.stream()
                .map(product -> modelMapper.map(product, ProductModel.class))
                .collect(Collectors.toList());
    }
}
