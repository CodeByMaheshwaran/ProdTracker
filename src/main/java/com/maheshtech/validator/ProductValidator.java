package com.maheshtech.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.maheshtech.entity.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Product.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
     
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product_name", "product_name.empty", "'Product Name' is Mandatory");	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product_price", "product_price.empty", "'Product Price' is Mandatory");	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product_desc", "product_desc.empty", "'Product Description' is Mandatory");	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "quantity.empty", "'Product Quantity' is Mandatory");	
	
	Product pr= (Product) target;
	Integer price = pr.getProduct_price();
	Integer quantity = pr.getQuantity();
	
	if(price!=null && price<0 ) {
		errors.rejectValue("product_price", "product_price.invalid" , "'Price Should be Positive Number'");
	}
	
	if(quantity!=null && quantity<0 ) {
		errors.rejectValue("quantity", "quantity.invalid" , "'Quantity Should be Positive Number'");
	}
	Integer discontinued = pr.getDiscontinued();
	if(discontinued == null) {
		errors.rejectValue("discontinued", "discontinued.not.selected" , "'Please Select an Option'");
	}
	}

}
