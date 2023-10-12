package com.project.RestApi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAll();

    List<Orders> findByName(String name);

}
