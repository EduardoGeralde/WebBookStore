package com.eduardoportfolio.store.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * The main objective of this class is show for the Spring MVC Servlet what classes should be read
 * and loaded..
 *
 */

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	//The Spring Security filter is loaded before the Spring MVC Servlet, so we need that this configuration objects
	//available before the Servlet, this is exactly what the geRootConfigClasses method do, it makes the classes to be read and loaded
	//into a Listener that is read when the server goes up
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SecurityConfiguration.class,AppWebConfiguration.class,
								JPAConfiguration.class, AmazonConfiguration.class};
	}

	//Returns one or more classes responsible for indicates which other classes has to be read during 
	//the loading of the application
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	//This method tells what's the pattern of address will be delegate to Spring MVC Servlet.
	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}
	
	//Multi-part configuration, receives Dynamic type object, which allow us, among other things, to
	//register our MultipartConfigElement configuration object. The constructor receiving only a empty
	//string, indicates that the web server itself will decide where the files will be store temporarily.
	@Override
	protected void customizeRegistration (Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}




