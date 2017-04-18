package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 *This class is a controller, responsible for requests from a client (browser)
 *
 */

//Tell SpringMVC that this class , is effectively, the responsible to meet 
//requests from a client(browser)
@Controller
public class HomeController {
	
	//Tell MVC which URL this method should respond (Binding).
	@RequestMapping("/")
	public String index(){
		//Here we'll load the products
		System.out.println("Loading products");
		return "hello-world";
	}
}
