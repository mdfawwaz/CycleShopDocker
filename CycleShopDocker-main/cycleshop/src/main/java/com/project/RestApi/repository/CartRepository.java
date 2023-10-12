package com.project.RestApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.RestApi.entity.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer>{
	
    Optional<CartItem> findByName(String name);
}
