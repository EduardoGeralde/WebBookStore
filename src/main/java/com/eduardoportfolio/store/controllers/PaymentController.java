package com.eduardoportfolio.store.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eduardoportfolio.store.models.PaymentData;
import com.eduardoportfolio.store.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			// integration code
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			try {
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				return "redirect:/payment/success";
			} catch (HttpClientErrorException exception) {
				return "redirect:/payment/error";
			}
		};
	}

	@RequestMapping(value = "/success")
	public String success() {
		return "payment/success";
	}

	@RequestMapping(value = "/error")
	public String error() {
		return "payment/error";
	}
}