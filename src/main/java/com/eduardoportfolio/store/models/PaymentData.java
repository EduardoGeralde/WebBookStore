package com.eduardoportfolio.store.models;

import java.math.BigDecimal;

//Our payment system which we are performing the integration, requires that the format of the data be like
//a Map, we need a key "value"associated with any value, that is why we need this class
public class PaymentData {
	
	private BigDecimal value;
	
	public PaymentData(){	
	}
	
	public PaymentData(BigDecimal value){
		this.value = value;
	}
	
	public BigDecimal getValue(){
		return value;
	}
}
