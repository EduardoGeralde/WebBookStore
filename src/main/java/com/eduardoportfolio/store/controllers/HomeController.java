package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Eduardo
 * 
 *This class is a controller, responsible to meet requests from a client (browser).
 *
 */

//@Controller annotation tells SpringMVC that this class , is effectively, the responsible to meet 
//requests from a client(browser).
@Controller
public class HomeController {
	
	//Tell MVC which URL this method should respond (Called Binding).
	@RequestMapping("/")
	public String index(){
		//Here we'll load the products
		System.out.println("Loading products");
		return "hello-world";
	}
	
	
	
	

}
