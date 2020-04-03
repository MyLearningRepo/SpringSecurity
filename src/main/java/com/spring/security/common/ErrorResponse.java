package com.spring.security.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement(name = "ErrorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	private int statusCode;
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private final String errorCode;

	
	public ErrorResponse() {
		super();
		this.statusCode = 0;
		this.errorCode = null;
	}

	public ErrorResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.errorCode = null;
	}

	public ErrorResponse(int statusCode, String message, String errorCode) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.errorCode = errorCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
