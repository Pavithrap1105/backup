package com.luxoft.Employeemanagement.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.luxoft.Employeemanagement.exception.ExcpetionDetails;
import com.luxoft.Employeemanagement.exception.ResourceAlreadyExistException;
import com.luxoft.Employeemanagement.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidException(MethodArgumentNotValidException exception) {
		Map<String, String> mapError = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			mapError.put(error.getField(), error.getDefaultMessage());
		});
		return mapError;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExcpetionDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ExcpetionDetails exceptionDetails = new ExcpetionDetails(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
		return new ResponseEntity<ExcpetionDetails>(exceptionDetails, exceptionDetails.getStatusCode());
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ExcpetionDetails> handleResourceAlreadyExistException(ResourceAlreadyExistException exception,
			WebRequest request) {
		ExcpetionDetails exceptionDetails = new ExcpetionDetails(HttpStatus.CONFLICT, exception.getLocalizedMessage());
		return new ResponseEntity<ExcpetionDetails>(exceptionDetails, exceptionDetails.getStatusCode());
	}

}
