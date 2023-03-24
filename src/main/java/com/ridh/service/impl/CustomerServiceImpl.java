package com.ridh.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CustomerEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CustomerModel;
import com.ridh.repo.CustomerRepo;
import com.ridh.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo daoImpl;

	@Override
	public CustomerModel createCustomer(CustomerModel customerModel) {
		// TODO Auto-generated method stub
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerModel, customerEntity);
		CustomerEntity saveEntity = daoImpl.save(customerEntity);
		CustomerModel newCustomerModel = new CustomerModel();
		BeanUtils.copyProperties(saveEntity, newCustomerModel);
		return newCustomerModel;
	}

	@Override
	public CustomerModel getCustomerById(Long id) throws RecordNotFoundException {
		Optional<CustomerEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CustomerEntity customerEntity = findById.get();
			CustomerModel newCustomerModel = new CustomerModel();
			BeanUtils.copyProperties(customerEntity, newCustomerModel);
			return newCustomerModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public CustomerModel updateCustomer(Long id, CustomerModel customerModel) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CustomerEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CustomerEntity customerEntity = findById.get();
			customerModel.setCustomerId(id);
			BeanUtils.copyProperties(customerModel, customerEntity);
			CustomerEntity saveEntity = daoImpl.save(customerEntity);
			CustomerModel newCustomerModel = new CustomerModel();
			BeanUtils.copyProperties(saveEntity, newCustomerModel);
			return newCustomerModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public String deleteCustomer(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CustomerEntity> findById = daoImpl.findById(id);
		String msg="";
		if (findById.isPresent()) {
			daoImpl.deleteById(id);
			msg="Deleted Succesfully!!!";
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
		return msg;

	}

}
