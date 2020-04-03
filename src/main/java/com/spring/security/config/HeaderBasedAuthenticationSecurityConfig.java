package com.spring.security.config;

import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.authentication.filter.HearderBasedAuthenticationFilter;
import com.spring.security.authentication.provider.HeaderBasedAuthenticationProvider;
import com.spring.security.authentication.validator.HeaderValidatorService;

public abstract class HeaderBasedAuthenticationSecurityConfig extends ApplicationSecurityConfig {

	@Override
	public HeaderBasedAuthenticationProvider getAuthenticationProvider() {
		return new HeaderBasedAuthenticationProvider(getHeaderValidatorService());
	}
	
	@Override
	public OncePerRequestFilter getAuthenticationFilter() {
		return new HearderBasedAuthenticationFilter(getAuthenticationHeaderName());
	}

	public abstract HeaderValidatorService getHeaderValidatorService();
	
	public abstract String getAuthenticationHeaderName();
}
