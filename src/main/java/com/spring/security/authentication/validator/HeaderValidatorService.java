package com.spring.security.authentication.validator;

import com.spring.security.authentication.AuthenticatedDetail;

public interface HeaderValidatorService {
	
	public AuthenticatedDetail validate(String id);

}
