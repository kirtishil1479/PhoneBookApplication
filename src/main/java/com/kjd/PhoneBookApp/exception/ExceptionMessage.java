package com.kjd.PhoneBookApp.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionMessage {
	private String errorcode;
	private String msg;
	private LocalDateTime localDateTime;
	

}
