package com.eduardoportfolio.store.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping("/products")
public class ProductsController {
	
	//Responsible to indicates the injection points inside the class (ProductDao).
	@Autowired
	private ProductDao productDao;
	
	//Tell MVC which URL this method should respond (Binding).
	@RequestMapping("/form")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types",BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Product product, RedirectAttributes redirectAttributes) {
		
		if (StringUtils.isEmpty(product.getTitle())){
			//We add some error validation
		}
		if (StringUtils.isEmpty(product.getDescription())){
			//We add some error validation
		}
		productDao.save(product);
		redirectAttributes.addFlashAttribute("success","Product successfully registered");
		return "redirect:products";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView ("products/list");
		modelAndView.addObject("products",productDao.list());
		return modelAndView;
	}
}


