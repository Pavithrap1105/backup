package com.luxoft.orderservice.service;

import org.springframework.stereotype.Service;

import com.luxoft.orderservice.dto.OrderRequest;
import com.luxoft.orderservice.model.Order;

@Service
public interface OrderService {

	public Order placeOrder(OrderRequest orderRequest);
}
