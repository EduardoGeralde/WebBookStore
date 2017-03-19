package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(){
		//Here we'll load the products
		System.out.println("Loading products");
		return "hello-world.jsp";
	}

}
