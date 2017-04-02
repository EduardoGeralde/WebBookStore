package com.eduardoportfolio.store.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class is a Product model (POJO) class, represents our product table in the database
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
	@NotBlank
	private String title;
	//Indicates that the attribute will be saved like a CLOB or BLOB in the DB
	@Lob
	@NotBlank
	private String description;
	//Used to define a collection of Embeddable objects
	@ElementCollection
	private List<Price> prices = new ArrayList<Price>();
	@Min(30)
	private int pages;
	@DateTimeFormat(iso=ISO.DATE)
	private Calendar releaseDate;
	private String summaryPath;
	
	
	public Integer getId() {
		return id;
	}
	public String getSummaryPath() {
		return summaryPath;
	}
	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
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
	
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}	
	
	@Override
	public String toString() {
		return "Product [title=" + title + ", description="
				+ description + ", numberPages=" + pages + "]";
	}
	
	public BigDecimal priceFor(BookType bookType) {
		return prices
				.stream()
				.filter(price -> price.getBookType().equals(bookType))
				.findFirst().get().getValue();
	}
}


