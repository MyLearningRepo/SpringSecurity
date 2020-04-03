package com.spring.security.authentication.excption.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.authentication.excption.AuthenticationFailedException;
import com.spring.security.common.ErrorResponse;

@Component
public class AuthenticationFailedExceptionHandler implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		AuthenticationFailedException basicAuthenticationException = (AuthenticationFailedException)authException;
		ErrorResponse errorResponse = new ErrorResponse(401, basicAuthenticationException.getMessage(), basicAuthenticationException.getErrorCode());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
	}

}
