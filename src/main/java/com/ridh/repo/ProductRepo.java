package com.ridh.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.CustomerEntity;
import com.ridh.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Long>{

}
