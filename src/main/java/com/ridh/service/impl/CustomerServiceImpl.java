package com.ridh.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CustomerEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CustomerModel;
import com.ridh.repo.CustomerRepo;
import com.ridh.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("CustomerServiceImpl:getCustomerById execution started !!");
		Optional<CustomerEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			log.info("Customer found!!");
			CustomerEntity customerEntity = findById.get();
			CustomerModel newCustomerModel = new CustomerModel();
			BeanUtils.copyProperties(customerEntity, newCustomerModel);
			log.info("Copy complete!!");
			log.info("CustomerServiceImpl:getCustomerById execution ended !!");
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
	        
	        // Check which fields are present in the customerModel argument and update only those fields in the entity
	        if (customerModel.getFirstName() != null) {
	            customerEntity.setFirstName(customerModel.getFirstName());
	        }
	        if (customerModel.getLastName() != null) {
	            customerEntity.setLastName(customerModel.getLastName());
	        }
	        if (customerModel.getEmailAddress() != null) {
	            customerEntity.setEmailAddress(customerModel.getEmailAddress());
	        }
	        if (customerModel.getPhoneNumber() != null) {
	            customerEntity.setPhoneNumber(customerModel.getPhoneNumber());
	        }
	        if (customerModel.getShippingAddress() != null) {
	            customerEntity.setShippingAddress(customerModel.getShippingAddress());
	        }
	        if (customerModel.getBillingAddress() != null) {
	            customerEntity.setBillingAddress(customerModel.getBillingAddress());
	        }
	        if (customerModel.getPaymentInformation() != null) {
	            customerEntity.setPaymentInformation(customerModel.getPaymentInformation());
	        }
	        
	        // Save the updated entity using the DAO
	        CustomerEntity savedEntity = daoImpl.save(customerEntity);
	        
	        // Create a new CustomerModel object and copy the properties of the saved entity
	        CustomerModel newCustomerModel = new CustomerModel();
	        BeanUtils.copyProperties(savedEntity, newCustomerModel);
	        
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

	@Override
	public void updateStatusIfInactive() {
		List<CustomerEntity> findAllCustomer = daoImpl.findAll();
		LocalDateTime now = LocalDateTime.now(); // current timestamp
		List<CustomerEntity> inactiveCustomers = findAllCustomer.stream()
				.filter(customer -> customer.getStatus().equals("Inactive"))
				.filter(customer -> Duration.between(customer.getCreatedOn(), now).toHours() > 24)
				.collect(Collectors.toList()); // collect all matching CustomerEntity objects into a list
		if (inactiveCustomers.isEmpty()) {
			// add a log statement if no CustomerEntity objects are found with a status of "Inactive"
			log.info("CustomerService:updateStatusIfInactive: No inactive customers found");
			return;
		}
		for (CustomerEntity inactiveCustomer : inactiveCustomers) {
			inactiveCustomer.setStatus("Active");
			daoImpl.save(inactiveCustomer);
			// add a log statement for each CustomerEntity object that is updated
			log.info("CustomerService:updateStatusIfInactive: Customer status updated: {}", inactiveCustomer.getFirstName());
		}
	}

}
