package com.eduardoportfolio.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo
 * 
 *
 */

@Controller
public class ProductsController {
	
	@RequestMapping("/products")
	public String save(Product product) {
		System.out.println("Registering the product"+product);
		return "products/ok";
	}
	
	@RequestMapping("/products/form")
	public String form(){
		return "products/form";
	}
}

