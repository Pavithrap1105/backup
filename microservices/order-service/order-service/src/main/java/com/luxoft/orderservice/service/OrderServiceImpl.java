package com.luxoft.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.orderservice.dto.OrderLineItemsDto;
import com.luxoft.orderservice.dto.OrderRequest;
import com.luxoft.orderservice.model.Order;
import com.luxoft.orderservice.model.OrderLineItems;
import com.luxoft.orderservice.repository.OrderServiceRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Override
	public Order placeOrder(OrderRequest orderRequest) {
		Order order= new Order();
		order.setOrderNumber(UUID.randomUUID().toString());  

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream().map(orderLineItem-> mapToDto(orderLineItem)).toList();
		order.setOrderLineItems(orderLineItems);
		
		return orderServiceRepository.save(order);
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		return orderLineItems;
	}

}
