package com.maheshtech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maheshtech.dao.DaoException;
import com.maheshtech.dao.ProductDao;
import com.maheshtech.entity.Product;
import com.maheshtech.validator.ProductValidator;

@Service
public class ProductService {
	@Autowired
	ProductDao htDao;
	
	public String getAllProducts(Model model) throws DaoException {
		model.addAttribute("pageTitle", "All Products");
		model.addAttribute("products", htDao.getAllProducts());
		return "show-products";
	}

	public String getProductByPriceRange(Model model, @RequestParam Integer min, @RequestParam Integer max)
			throws DaoException {
		model.addAttribute("pageTitle", "Products priced between $" + min + " and $" + max);
		model.addAttribute("products", htDao.getAllProductsByPriceRange(min, max));
		return "show-products";
	}
	
	public String getProductDetails(Model model, @RequestParam Integer id) throws DaoException {
		model.addAttribute("product", htDao.getProduct(id));
		return "product-details";
	}
	
	public String addProduct(Model model) {
		model.addAttribute("pr", new Product());
		return "product-form";
	}
	
	public String editProduct(Model model, @RequestParam Integer id) throws DaoException {
		model.addAttribute("pr", htDao.getProduct(id));
		return "product-form";
	}
	
	public String saveProduct(@ModelAttribute("pr") Product p, BindingResult errors) throws DaoException {

		ProductValidator pv = new ProductValidator();
		pv.validate(p, errors);

		if (errors.hasErrors()) {
			return "product-form";
		}
		if (p.getProduct_ID() == null) {
			htDao.addProduct(p);
		} else {
			htDao.updateProduct(p);
		}
		return "redirect:product-details?id=" + p.getProduct_ID();
	}


}
