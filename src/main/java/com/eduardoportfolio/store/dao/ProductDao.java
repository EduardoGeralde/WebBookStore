package com.eduardoportfolio.store.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.store.models.Product;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product) {
		manager.persist(manager);
	}
	
	
}
