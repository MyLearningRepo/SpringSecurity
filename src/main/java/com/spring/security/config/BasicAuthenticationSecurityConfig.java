package com.spring.security.config;

import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.authentication.filter.BasicAuthenticationFilter;
import com.spring.security.authentication.provider.BasicAuthenticationProvider;
import com.spring.security.authentication.validator.CredentialValidatorService;

public abstract class BasicAuthenticationSecurityConfig extends ApplicationSecurityConfig {

	@Override
	public BasicAuthenticationProvider getAuthenticationProvider() {
		return new BasicAuthenticationProvider(getCredentialValidatorService());
	}
	
	public abstract CredentialValidatorService getCredentialValidatorService();
	
	@Override
	public OncePerRequestFilter getAuthenticationFilter() {
		return new BasicAuthenticationFilter();
	}

}
