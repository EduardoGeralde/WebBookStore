package com.eduardoportfolio.store.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eduardoportfolio.store.controllers.HomeController;

/**
 * 
 * @author Eduardo
 * 
 * This class was made to make some configuration. The InternalResourceViewResolver class, saves the path
 * for the root directory and for the suffix to add in any return from the controller methods.
 *
 */

//Enables many features like (Objects conversion for XML and JSON, Validating using specification,
//support to the RSS generator, and so one...
@EnableWebMvc
//through this annotation we indicate what package should be read.
@ComponentScan(basePackageClasses={HomeController.class})
public class AppWebConfiguration {

	//Shows the Spring that the return from this method have to be registered as an object managed by the
	//container. This objects in general are called Beans.
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
