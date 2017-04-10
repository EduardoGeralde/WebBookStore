package com.eduardoportfolio.store.service;

import java.math.BigDecimal;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.eduardoportfolio.store.models.PaymentData;

public class PaymentIntegration implements Runnable{
	
	private DeferredResult<String> result;
	private BigDecimal value;
	private RestTemplate restTemplate;
	
	public PaymentIntegration(DeferredResult<String> result, BigDecimal value, RestTemplate restTemplate){
		super();
		this.result = result;
		this.value = value;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public void run(){
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		try{
			String response = restTemplate.postForObject(uriToPay, new PaymentData(value),String.class);
			//notification line
			result.setResult("redirect:/payment/success");
		} catch (HttpClientErrorException exception){
			result.setResult("redirect:/payment/error");
		}
	}

}