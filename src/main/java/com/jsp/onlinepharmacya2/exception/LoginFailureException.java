package com.jsp.onlinepharmacya2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginFailureException extends RuntimeException {

	private String message;
	
}
