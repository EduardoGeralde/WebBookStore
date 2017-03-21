package com.eduardoportfolio.store.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eduardoportfolio.store.controllers.HomeController;
import com.eduardoportfolio.store.dao.ProductDao;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class has some configurations. The InternalResourceViewResolver class, saves the path
 * for the root directory and file suffix to add in any return from the controller methods.
 *
 */

//Enables many features like (Objects conversion for XML and JSON, Validating using specification,
//support to the RSS generator, and so one...
@EnableWebMvc
//through this annotation we indicate what package should be read.
@ComponentScan(basePackageClasses={HomeController.class, ProductDao.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

	//Shows the Spring that the return from this method have to be registered as an object managed by the
	//container. This objects in general are called Beans.
	@Bean
	//Save the information of the home folder and file suffix
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
