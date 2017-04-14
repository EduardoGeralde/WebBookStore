package com.eduardoportfolio.store.conf;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Should be placed in top of Spring Security Configuration classes responsible for effective handle
//the access rules.
@EnableWebSecurity
//We inherit WebSecurityConfigurerAdapter class that already provide all infrastructure ready to start 
//our security configurations. This annotation also loads other components, like SecurityExpressionLanguage
//and WebInvocationPrivilegeEvaluator
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/products/form").hasRole("ADMIN")
		.antMatchers("/shopping/**").permitAll()
		.antMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
		.antMatchers("/products/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
	}
}
