package com.eduardoportfolio.store.controllers;

import javax.transaction.Transactional;

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
@Transactional
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

