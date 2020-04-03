package com.spring.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring.security.authentication.AuthenticatedDetail;
import com.spring.security.authentication.excption.AuthenticationFailedException;
import com.spring.security.authentication.token.BasicAuthenticationToken;
import com.spring.security.authentication.validator.CredentialValidatorService;

public class BasicAuthenticationProvider implements AuthenticationProvider{

	private CredentialValidatorService credentialValidatorService;
	
	public BasicAuthenticationProvider(CredentialValidatorService credentialValidatorService) {
		this.credentialValidatorService = credentialValidatorService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication == null) {
			throw new AuthenticationFailedException("Authentication failed", "AUTHENTICATION_FAILED");
		}
		String principal = (String)authentication.getPrincipal();
		String credential = (String)authentication.getCredentials();
		if(principal == null || credential == null) {
			throw new AuthenticationFailedException("Authorization header is null or invalid", "AUTHENTICATION_FAILED");
		}
		AuthenticatedDetail authenticatedDetail = credentialValidatorService.validateCredential(principal, credential);
		if(authenticatedDetail == null) {
			throw new AuthenticationFailedException("Authentication information is invalid.", "AUTHENTICATION_FAILED");
		}
		return new BasicAuthenticationToken(authenticatedDetail, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (BasicAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

}
