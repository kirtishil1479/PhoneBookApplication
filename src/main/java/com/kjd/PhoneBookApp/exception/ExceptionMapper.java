package com.kjd.PhoneBookApp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionMapper {
	
	@ExceptionHandler(value=NoContactFoundException.class)
	public ResponseEntity<ExceptionMessage> nodataFoundEX(NoContactFoundException n){
		ExceptionMessage exMsg=new ExceptionMessage();
		exMsg.setErrorcode("CST2765");
		exMsg.setMsg(n.getMessage());
		exMsg.setLocalDateTime(LocalDateTime.now());
		
		
		return new ResponseEntity<ExceptionMessage>(exMsg,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}

}
