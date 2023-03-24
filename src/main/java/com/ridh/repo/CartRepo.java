package com.ridh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridh.entity.CartEntity;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,Long>{
}
