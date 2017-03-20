package com.eduardoportfolio.store.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 *
 */
//Indicates that the class have to be represented by a table in DB
@Entity
public class Product {
	//Indicates that the attribute is a primary key
	@Id
	//Indicates how the primary key will be generated
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	//Indicates that the attribute will be saved like a CLOB or BLOB in the DB
	@Lob
	private String description;
	private int pages;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}	
	
	@Override
	public String toString() {
		return "Produto [title=" + title + ", description="
				+ description + ", numberPages=" + pages + "]";
	}
}


