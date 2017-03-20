package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.store.models.Product;

@Controller
public class ProductsController {
	
	@RequestMapping("/products")
	public String save(Product product) {
		System.out.println("Registering the product"+product);
		return "products/ok";
	}
}


