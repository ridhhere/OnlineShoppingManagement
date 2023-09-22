package com.ridh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.OrderEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Long>{

}
