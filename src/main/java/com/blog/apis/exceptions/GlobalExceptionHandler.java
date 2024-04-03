package com.blog.apis.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice  //this is must without not working here we can handle error in whole controller
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public String noSuchExceptionHandler(NoSuchElementException ex){
		String message = ex.getMessage();
		return message+" data not present in database";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
	    Integer userId = ex.getUserId();
	    String errorMessage = "User with ID " + userId + " not present in the database";
	    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) //MethodArgumentNotValidException
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValiException(MethodArgumentNotValidException ex)
	{
		Map<String,String> resp= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName =((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
}
