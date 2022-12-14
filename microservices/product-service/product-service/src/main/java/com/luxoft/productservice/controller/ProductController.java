package com.luxoft.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.productservice.dto.ProductRequest;
import com.luxoft.productservice.dto.ProductResponse;
import com.luxoft.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) {
		productService.createProduct(product);
		return new ResponseEntity(product, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		List<ProductResponse> products = productService.getAllProducts();
		return new ResponseEntity(products, HttpStatus.OK);
	}
}
