package com.spring.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.authentication.excption.handler.AuthenticationFailedExceptionHandler;

public abstract class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
		auth.eraseCredentials(true);
		return;
	}

	/*@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(new AuthenticationFailedExceptionHandler())
			.and()
			.addFilterBefore(getAuthenticationFilter(), BasicAuthenticationFilter.class);
			
	}*/
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(new AuthenticationFailedExceptionHandler())
			.and()
			.oauth2ResourceServer();
			
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		String[] ann = getAnnonymousEndpointUri();
		if(ann != null && ann.length > 0) {
			webSecurity.ignoring().antMatchers(ann);
		}
	}
	
	public abstract AuthenticationProvider getAuthenticationProvider() ;
	
	public abstract OncePerRequestFilter getAuthenticationFilter() ;
	
	public abstract String[] getAnnonymousEndpointUri();

}
