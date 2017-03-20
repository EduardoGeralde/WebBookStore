package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductsController {
	
	@RequestMapping("/products")
	public String save() {
		System.out.println("Registering the product");
		return "products/ok";
	}
}


