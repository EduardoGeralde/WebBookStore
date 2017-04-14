package com.eduardoportfolio.store.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	//In order to implement the search (for user to apply the rules) in a best way for each application, Spring Security provides the 
	//UserDetailsService interface.
	@Autowired
	private UserDetailsService users;
	//We use a overload of the configure() method that receives an AutheticationManagerBuilder object, that allow us associate a new 
	//UserDetailService with Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
}
