package com.eduardoportfolio.store.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.store.dao.ProductDao;
import com.eduardoportfolio.store.models.BookType;
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
	
	@RequestMapping("/form")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types",BookType.values());
		return modelAndView;
	}
	
	@RequestMapping("/products")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView ("product/list");
		modelAndView.addObject("products",productDao.list());
		return modelAndView;
	}
}

