package com.luxoft.productservice.service;

import java.util.List;

import com.luxoft.productservice.dto.ProductRequest;
import com.luxoft.productservice.dto.ProductResponse;
import com.luxoft.productservice.model.Product;

public interface ProductService {
	
	public Product createProduct(ProductRequest product);

	public List<ProductResponse> getAllProducts();

}
