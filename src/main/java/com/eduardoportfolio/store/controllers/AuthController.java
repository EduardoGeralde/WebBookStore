package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * 
 *
 */

@Controller
public class AuthController {
	
	@RequestMapping("/login")
	public String loginPage(){
		return "auth/login";
	}

}
