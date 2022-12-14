package com.luxoft.productservice.dto;

import java.math.BigDecimal;

import com.luxoft.productservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
	
	private String name;
	private String description;
	private BigDecimal price;

}
