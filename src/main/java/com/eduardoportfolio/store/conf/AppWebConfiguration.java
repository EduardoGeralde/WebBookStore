package com.eduardoportfolio.store.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eduardoportfolio.store.controllers.HomeController;
import com.eduardoportfolio.store.dao.ProductDao;
import com.eduardoportfolio.store.infra.FileSaver;
import com.eduardoportfolio.store.models.ShoppingCart;
import com.eduardoportfolio.store.viewresolver.JsonViewResolver;
import com.google.common.cache.CacheBuilder;

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
@ComponentScan(basePackageClasses={HomeController.class, ProductDao.class,
														 FileSaver.class,ShoppingCart.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
	
	//Shows the Spring that the return from this method have to be registered as an object managed by the
	//container. This objects in general are called Beans.
	@Bean
	//Save the information of the home folder and file suffix
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//this line exposes all our managed objects to be available for use through expression language
		//resolver.setExposeContextBeansAsAttributes(true);
		//we can pass the exact class name to register and expose this objects for expression language
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}
	
	//Add class Responsible for effectively save the objects to be cached, we use here a simple implementation
	//that is already done inside the Spring itself, the ConcurrentMapCacheManager.
	/**@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}
	*/
	
	@Bean
	//Guava cache implementation
	public CacheManager cacheManager(){
		CacheBuilder<Object,Object> builder = CacheBuilder.newBuilder().maximumSize(100)
															.expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(builder);
		return cacheManager;
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
	//Teach Spring that has always to use this formatting instead always write with @DateTimeFormat 
	//for example.
	public FormattingConversionService mvcConversionService(){
		
		DefaultFormattingConversionService conversionService = 
								new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	//Create a ViewResolver list (XML, JSON, etc) and pass to a ContentNegotiatingViewResolver object
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(internalResourceViewResolver());
		resolvers.add(new JsonViewResolver());
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}
}

