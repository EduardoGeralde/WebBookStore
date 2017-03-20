package com.eduardoportfolio.store.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 */

//Indicate that this class besides being managed for the Spring, it is responsible for the data access.
@Repository
public class ProductDao {

	//Indicates Injection of Entity Manager 
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product) {
		manager.persist(manager);
	}
	
	
}
