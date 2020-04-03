package com.spring.security.authentication.excption;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException{
	
private static final long serialVersionUID = 1L;
	
	private String errorCode = null;
	public AuthenticationFailedException(String msg) {
		super(msg);
	}
	
	public AuthenticationFailedException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
