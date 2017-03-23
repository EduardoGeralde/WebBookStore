package com.eduardoportfolio.store.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class isolates configurations such as driver, login, password, database url, JPA implementation,
 * and so on..
 * 
 */

//Indicates that we are using Spring transactional control.
@EnableTransactionManagement
public class JPAConfiguration {
	
	//This annotationIndicate that the objects created for this method, will be manage by Spring, 
	//and can be inject in any part of the code.
	@Bean
	//Is the persistence.xml abstraction, generally necessary to have JPA working.
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"com.eduardoportfolio.store.models"});
		//Represents our implementation choice, Hibernate.
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		
		return em;
	}
	
	@Bean
	//Configure the database parameters.
	public DataSource dataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/WebBookStore");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}
	
	//Sets additional properties, like auto-update, sql dialect, show sql in console, and so on...
	private Properties additionalProperties() {
		
		Properties properties = new Properties ();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
	
	@Bean
	//Besides indicate that we are using Spring transactional control with annotation in the class,
	//we have to tell which implementation we choose, in this case, we are using implementation for the
	//JPA, we could use implementation directly for Hibernate or even to JDBC.
	public PlatformTransactionManager transactionManager (EntityManagerFactory emf){
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}



