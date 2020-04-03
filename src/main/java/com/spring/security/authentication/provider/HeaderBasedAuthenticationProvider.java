package com.spring.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring.security.authentication.AuthenticatedDetail;
import com.spring.security.authentication.excption.AuthenticationFailedException;
import com.spring.security.authentication.token.HeaderAuthenticationToken;
import com.spring.security.authentication.validator.HeaderValidatorService;

public class HeaderBasedAuthenticationProvider implements AuthenticationProvider{

	private HeaderValidatorService headerValidatorService;
	
	public HeaderBasedAuthenticationProvider(HeaderValidatorService headerValidatorService) {
		this.headerValidatorService = headerValidatorService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		HeaderAuthenticationToken authenticationToken = (HeaderAuthenticationToken)authentication;
		String auhtenticateValue = authenticationToken.getValue();
		if(auhtenticateValue == null || auhtenticateValue.isEmpty()) {
			throw new AuthenticationFailedException(authenticationToken.getName()+" value is null or empty", "AUTHENTICATION_FAILED");
		}
		AuthenticatedDetail authenticatedDetail = headerValidatorService.validate(auhtenticateValue);
		if(authenticatedDetail == null) {
			throw new AuthenticationFailedException("Invalid "+authenticationToken.getName(), "AUTHENTICATION_FAILED");
		}
		return new HeaderAuthenticationToken(authenticatedDetail, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (HeaderAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

}
