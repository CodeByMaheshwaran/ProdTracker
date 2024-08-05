package com.maheshtech.web.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maheshtech.dao.DaoException;
import com.maheshtech.dao.ProductDao;
import com.maheshtech.entity.ErrorResponse;
import com.maheshtech.entity.Product;
import com.maheshtech.entity.ProductList;

@RequestMapping("/api/products")
@RestController
public class ProductResource {
	@Autowired
	ProductDao htDao;

	@RequestMapping(method = RequestMethod.GET, produces= {"application/json","application/xml"})
	public ResponseEntity<ProductList> getAllProducts() throws DaoException {
		ProductList pr = new ProductList();
		pr.setProducts(htDao.getAllProducts());
		return ResponseEntity.ok(pr);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces= {"application/json","application/xml"})
	public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) throws DaoException {
		Product pr = htDao.getProduct(id);
		if (pr == null) {
			ErrorResponse er=new ErrorResponse();
			er.setMessage("No Product found");
			er.setData(id);
			return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(pr);
	}

	@RequestMapping(method = RequestMethod.POST,
			produces= {"application/json","application/xml"},
			consumes= {"application/json","application/xml"})
	public ResponseEntity<?> addProduct(@RequestBody Product pr) {
		try {
			htDao.addProduct(pr);
			pr = htDao.getProduct(pr.getProduct_ID());
			return ResponseEntity.ok(pr);
		} catch (DaoException e) {
			
			ErrorResponse er=new ErrorResponse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", 
			produces= {"application/json","application/xml"},
			consumes= {"application/json","application/xml"})
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product pr) {
		try {
			pr.setProduct_ID(id);
			htDao.updateProduct(pr);
			pr = htDao.getProduct(pr.getProduct_ID());
			return ResponseEntity.ok(pr);
		} catch (DaoException e) {
			ErrorResponse er=new ErrorResponse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}", 
			produces= {"application/json","application/xml"})
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

		try {
			Product pr = htDao.getProduct(id);
			if (pr == null) {
				ErrorResponse er=new ErrorResponse();
				er.setMessage("No Product found");
				er.setData(pr);
				return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
			}
			htDao.deleteProduct(id);
			pr = htDao.getProduct(id);
			return ResponseEntity.ok(pr);
		} catch (DaoException e) {
			ErrorResponse er=new ErrorResponse();
			er.setMessage(e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
