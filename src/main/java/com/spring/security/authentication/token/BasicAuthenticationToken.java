package com.spring.security.authentication.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.spring.security.authentication.AuthenticatedDetail;

public class BasicAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthenticatedDetail authenticatedDetail = null;
	private final String principal;
	private String credential;
	
	public BasicAuthenticationToken(String principal, String credential) {
		super(null);
		this.principal = principal;
		this.credential = credential;
	}
	
	public BasicAuthenticationToken(AuthenticatedDetail authenticatedDetail,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.authenticatedDetail = authenticatedDetail;
		this.principal = authenticatedDetail.getId();
		this.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return this.credential;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	@Override
	public AuthenticatedDetail getDetails() {
		return this.authenticatedDetail;
	}

}
