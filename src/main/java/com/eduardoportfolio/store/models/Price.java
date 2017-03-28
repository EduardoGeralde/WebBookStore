package com.eduardoportfolio.store.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This embeddable class represents the lists of Book types and its values inside Product class.
 * 
 */

@Embeddable
public class Price {
	
	@Column(scale = 2)
	private BigDecimal value;
	private BookType bookType;
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	
	
}
