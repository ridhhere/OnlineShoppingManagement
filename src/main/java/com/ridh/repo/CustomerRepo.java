package com.ridh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,Long>{

}
