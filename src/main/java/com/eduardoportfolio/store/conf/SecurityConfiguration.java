package com.eduardoportfolio.store.conf;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Should be placed in top of Spring Security Configuration classes responsible for effective handle
//the access rules.
@EnableWebSecurity
//We inherit WebSecurityConfigurerAdapter class that already provide all infrastructure ready to start 
//our security configurations. This annotation also loads other components, like SecurityExpressionLanguage
//and WebInvocationPrivilegeEvaluator
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
}
