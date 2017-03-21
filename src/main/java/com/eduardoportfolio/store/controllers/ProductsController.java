package com.eduardoportfolio.store.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.store.dao.ProductDao;
import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 *
 */

@Controller
@Transactional
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/products")
	public String save(Product product) {
		productDao.save(product);
		return "products/ok";
	}
	
	@RequestMapping("/products/form")
	public String form(){
		return "products/form";
	}
}

