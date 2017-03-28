package com.eduardoportfolio.store.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eduardoportfolio.store.models.Product;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * This class is specialized in logic validations
 * 
 */

public class ProductValidator implements Validator{
	
	@Override
	public void validate (Object target, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description", "field.required");
		
		//Use rejectValue to add new message in the error list
		Product product = (Product) target;
		if(product.getPages() == 0){
			errors.rejectValue("pages", "field.required");
		}
	}

	@Override
	//Receives the object we want to validate and returns if the validator can handle with this object.
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}
}
