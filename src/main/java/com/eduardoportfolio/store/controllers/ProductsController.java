package com.eduardoportfolio.store.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardoportfolio.store.dao.ProductDao;
import com.eduardoportfolio.store.infra.FileSaver;
import com.eduardoportfolio.store.models.BookType;
import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class is a controller responsible for all products transactions, flows, request.
 *
 */

//Tell SpringMVC that this class , is effectively, the responsible to meet 
//requests from a client(browser).
@Controller
//Indicates that this methods needs transaction.
@Transactional
@RequestMapping("/products")
public class ProductsController {
	
	/**
	 * Do not need this customized validator anymore
	 * 
	//Indicates that always when a request come to this controller, this method has to be called
	@InitBinder
	//Just telling which validator Spring has to use. The name can be anyone, the important is receive
	//a WebDataBinder and register a new validators.
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}
	*/
	
	//Responsible to indicates the injection points inside the class (ProductDao).
	@Autowired
	private ProductDao productDao;
	@Autowired
	private FileSaver fileSaver;
	
	//Tell MVC which URL this method should respond (Binding)
	@RequestMapping("/form")
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types",BookType.values());
		return modelAndView;
	}
	
	/**
	@RequestMapping(method=RequestMethod.GET,value="json")
	//Indicates that the return method has to be used directly in the response body. If we do not use this
	//annotation, Springs will search for a page, that is the normal behavior.
	@ResponseBody
	//Method to integrate with a external application, sending a list in JSON format.
	public List<Product> listJson() {
		return productDao.list();
	}
	*/
	
	@RequestMapping(method=RequestMethod.POST, name="saveProduct")
	@CacheEvict(value="books", allEntries=true)
	public ModelAndView save(MultipartFile summary,@Valid Product product, 
					BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println(summary.getName()+";"+summary.getOriginalFilename());
		if(bindingResult.hasErrors()){
			return form(product);
		}
		String webPath = fileSaver.write("uploaded-images",summary);
		product.setSummaryPath(webPath);
		productDao.save(product);
		
		redirectAttributes.addFlashAttribute("success","Product successfully registered");
		return new ModelAndView("redirect:products");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable (value="books")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView ("products/list");
		modelAndView.addObject("products",productDao.list());
		return modelAndView;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDao.find(id);
		modelAndView.addObject("product",product);
		return modelAndView;
	}
}



