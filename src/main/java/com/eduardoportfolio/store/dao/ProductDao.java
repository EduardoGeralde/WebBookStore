package com.eduardoportfolio.store.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class represents our Product CRUD, it's responsible to access the data.
 * 
 */

//Indicate that this class besides being managed for the Spring, it is responsible for the data access.
@Repository
public class ProductDao {

	//Indicates Injection of Entity Manager 
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product) {
		manager.persist(product);
	}
	
	public List<Product> list(){
		return manager.createQuery("select distinct (p) from Product p join fetch p.prices",
									Product.class).getResultList();
	}
	
	public Product find(Integer id) {
		TypedQuery<Product> query = manager
				.createQuery(
						"select distinct(p) from Product p join fetch p.prices where p.id=:id",
						Product.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
	public void delete(Product product) {
		manager.remove(product);
	}
	
	public void update(Product product) {
		manager.refresh(product);
	}
}
