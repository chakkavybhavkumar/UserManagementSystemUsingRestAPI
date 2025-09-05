package com.user.Exception;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.entity.UserDetails;

@RestControllerAdvice
public class UserExceptionHandler
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<UserDetails> validexceptionhandler(MethodArgumentNotValidException methodexception)
	{
		System.out.println("Data Not Valid");
		Map<String, String> map=new HashMap<String, String>();
		methodexception.getBindingResult().getAllErrors().forEach(error ->
		{
			String fieldname = ((FieldError) error).getField();
			String message=error.getDefaultMessage();
			map.put(fieldname, message);
		});
		return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(Invalidentryexception.class)
	public ResponseEntity<?> invaliddataexceptionhandler(Invalidentryexception invalidentryexception)
	{
		String msg=invalidentryexception.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}

}
