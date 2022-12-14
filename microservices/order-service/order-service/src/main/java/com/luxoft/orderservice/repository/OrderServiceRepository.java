package com.luxoft.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.orderservice.model.Order;

public interface OrderServiceRepository extends JpaRepository<Order, Long> {

}
