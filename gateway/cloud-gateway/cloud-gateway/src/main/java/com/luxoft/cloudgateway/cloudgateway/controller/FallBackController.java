package com.luxoft.cloudgateway.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

	@GetMapping("/employee-details-fallback")
	public String employeeManagementFallback() {
		return "Employee Management is taking too long to respond or is down....Please try again later!!";
	}
	
	
	
	
}
