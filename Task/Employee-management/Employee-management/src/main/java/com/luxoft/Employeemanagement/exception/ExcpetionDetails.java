package com.luxoft.Employeemanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcpetionDetails {
	
	private HttpStatus statusCode;
	private String message;

}
