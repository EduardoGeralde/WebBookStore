package com.eduardoportfolio.store.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Eduardo
 * 
 * The main objective of this class is show for the Spring MVC Servlet what classes should be read
 * and loaded.
 *
 */

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	//Returns one or more classes responsible for indicates which other classes has to be read during 
	//the loading of the application.
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}

	//This method tells what's the pattern of address will be delegate to Spring MVC Servlet.
	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}

}




