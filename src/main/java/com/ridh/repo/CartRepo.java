package com.ridh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.CartEntity;
import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,Long>{

	CartEntity findByCustomerAndProduct(CustomerEntity customer, ProductEntity product);

	List<CartEntity> findByCustomer(CustomerEntity customer);

	ProductEntity[] findByProduct(ProductEntity product);

//	List<CartEntity> findByCustomerId(Long customerId);
}
