package com.spring.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.authentication.token.HeaderAuthenticationToken;

public class HearderBasedAuthenticationFilter extends OncePerRequestFilter {
	
	public final String authenticateHeaderName;
	
	public HearderBasedAuthenticationFilter(String headerName) {
		this.authenticateHeaderName = headerName;
	}
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authenticateHeaderValue = request.getHeader(authenticateHeaderName);
		SecurityContextHolder.getContext().setAuthentication(
				new HeaderAuthenticationToken(this.authenticateHeaderName ,authenticateHeaderValue));  
		filterChain.doFilter(request, response);
	}

}
