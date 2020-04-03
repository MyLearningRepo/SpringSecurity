package com.spring.security.authentication.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.authentication.token.BasicAuthenticationToken;

public class BasicAuthenticationFilter extends OncePerRequestFilter {
	
	public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		BasicAuthenticationToken basicAuthenticationToken  = new BasicAuthenticationToken("","");
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if(authorizationHeader != null) {
			authorizationHeader = authorizationHeader.trim();
			if (StringUtils.startsWithIgnoreCase(authorizationHeader, AUTHENTICATION_SCHEME_BASIC)) {
				byte[] base64Token = authorizationHeader.substring(6).getBytes(StandardCharsets.UTF_8);
				try {
					byte[] decoded = Base64.getDecoder().decode(base64Token);
					String token = new String(decoded,  StandardCharsets.UTF_8);
					int delim = token.indexOf(":");
					if (delim != -1) {
						basicAuthenticationToken  = new BasicAuthenticationToken(token.substring(0, delim), token.substring(delim + 1));
					}
				}
				catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		SecurityContextHolder.getContext().setAuthentication(basicAuthenticationToken);  
		filterChain.doFilter(request, response);
	}

}
