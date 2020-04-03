package com.spring.security.authentication.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.spring.security.authentication.AuthenticatedDetail;

public class HeaderAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	
	public HeaderAuthenticationToken(String name, String value) {
		super(null);
		this.name = name;
		this.value = value;
	}
	
	public HeaderAuthenticationToken(AuthenticatedDetail authenticatedDetail,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.setAuthenticated(true);
		this.setDetails(authenticatedDetail);
	}

	@Override
	public Object getPrincipal() {
		if(super.getDetails() == null) {
			return null;
		}
		return ((AuthenticatedDetail)super.getDetails()).getId() ;
	}
	
	public AuthenticatedDetail getDetail() {
		return ((AuthenticatedDetail)super.getDetails());
	}

	@Override
	public String getCredentials() {
		return null;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
}
