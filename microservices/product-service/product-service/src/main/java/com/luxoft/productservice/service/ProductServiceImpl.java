package com.luxoft.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.productservice.dto.ProductRequest;
import com.luxoft.productservice.dto.ProductResponse;
import com.luxoft.productservice.model.Product;
import com.luxoft.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(ProductRequest product) {
		Product prod = Product.builder().name(product.getName()).description(product.getDescription())
				.price(product.getPrice()).build();
		productRepository.save(prod);
		return prod;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(product -> mapToProductResponse(product)).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
	}

}
