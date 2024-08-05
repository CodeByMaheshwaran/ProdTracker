package com.maheshtech.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.maheshtech.entity.Product;

@Transactional(rollbackFor = {DaoException.class}, readOnly = true)
public interface ProductDao {
	// CRUD operations
	@Transactional(readOnly = false)
	public default void addProduct(Product product) throws DaoException {
	throw new DaoException("Method not Impemented");
	}
	@Transactional(readOnly = false)
	public default void updateProduct(Product product) throws DaoException{
		throw new DaoException("Method not Impemented");
		}
    
	public default Product getProduct(Integer productID) throws DaoException{
		throw new DaoException("Method not Impemented");
		}
	@Transactional(readOnly = false)
	public default void deleteProduct(Integer productID) throws DaoException{
		throw new DaoException("Method not Impemented");
		}

	// Query Operations
	public default List<Product> getAllProducts() throws DaoException {
		throw new DaoException("Method not Impemented");
		}

	public default List<Product> getAllProductsByPriceRange(int min, int max) throws DaoException{
		throw new DaoException("Method not Impemented");
		}

	public default long count() throws DaoException {
		throw new DaoException("Method not Impemented");
		}
}

