package com.maheshtech.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maheshtech.dao.DaoException;
import com.maheshtech.entity.Product;
import com.maheshtech.validator.ProductValidator;
import com.maheshtech.web.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;

	@RequestMapping(method = RequestMethod.GET, path = "/all-products")
	public String getAllProducts(Model model) throws DaoException {
		return  service.getAllProducts(model);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/products-by-price-range")
	public String getProductByPriceRange(Model model, @RequestParam Integer min, @RequestParam Integer max)
			throws DaoException {
	   return service.getProductByPriceRange(model, min, max);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/product-details")
	public String getProductDetails(Model model, @RequestParam Integer id) throws DaoException {
	 return service.getProductDetails(model, id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/add-product")
	public String addProduct(Model model) {
		return service.addProduct(model);
	}

	@RequestMapping(method = RequestMethod.GET, path = "edit-product")
	public String editProduct(Model model, @RequestParam Integer id) throws DaoException {
		return service.editProduct(model, id);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/save-product")
	public String saveProduct(@ModelAttribute("pr") Product p, BindingResult errors) throws DaoException {


		return service.saveProduct(p, errors);
	}

	
}
