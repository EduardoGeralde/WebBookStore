package com.eduardoportfolio.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.store.dao.ProductDao;
import com.eduardoportfolio.store.models.BookType;
import com.eduardoportfolio.store.models.Product;
import com.eduardoportfolio.store.models.ShoppingCart;
import com.eduardoportfolio.store.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer productId, BookType bookType){
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/products");
	}
	
	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDao.find(productId);
		ShoppingItem item = new ShoppingItem(product,bookType);
		return item;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String items(){
		return "shoppingCart/items";
	}
}
