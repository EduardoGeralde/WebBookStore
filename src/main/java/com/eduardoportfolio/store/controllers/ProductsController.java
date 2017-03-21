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
 * This class is a controller responsible for all products transactions and request 
 *
 */

//Tell SpringMVC that this class , is effectively, the responsible to meet 
//requests from a client(browser).
@Controller
//Indicates that this methods needs transaction.
@Transactional
public class ProductsController {
	
	//Responsible to indicates the injection points inside the class (ProductDao).
	@Autowired
	private ProductDao productDao;
	
	//Tell MVC which URL this method should respond (Binding).
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

