package com.eduardoportfolio.store.infra;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * 
 *
 */

//We can use the successHandler method in the configure method from Security Configuration, and pass as a 
//argument a AuthenticationSuccessHandler object (like this) and use the response method to redirect the
//user to the location that we find most appropriate.
public class RedirectAfterLogin implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication auth) throws IOException, ServletException {
		
		response.sendRedirect("/home");
	}

}
