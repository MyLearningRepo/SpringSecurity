package com.spring.security.authentication.validator;

import com.spring.security.authentication.AuthenticatedDetail;

public interface CredentialValidatorService {
	
	public AuthenticatedDetail validateCredential(String id, String password);

}
