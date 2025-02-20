package com.abc.EmployeeManagement.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleArgumentValidException(MethodArgumentNotValidException ex){
		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		Map<String,Object> allErrors=new HashMap<>();
		allErrors.put("timestamp", LocalDateTime.now());
		allErrors.put("errors", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(allErrors);
	}
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<String> handleResourseNotFoundException(ResourseNotFoundException ex){
		return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}
}
