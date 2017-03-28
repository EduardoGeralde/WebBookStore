package com.eduardoportfolio.store.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
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
 * for the root directory and file suffix to add in any return from the controller methods
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
	
	@Bean (name="messageSource")
	//Tells Spring to use the messages.properties file, to change the validation error messages
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService(){
		
		DefaultFormattingConversionService conversionService = 
								new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
}
