package com.ridh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.CheckoutEntity;

@Repository
public interface CheckoutRepo extends JpaRepository<CheckoutEntity,Long>{

}
