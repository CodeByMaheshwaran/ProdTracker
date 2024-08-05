package com.maheshtech.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.maheshtech.entity.Product;

@SuppressWarnings("unchecked")
@Repository("htDao")
public class HibernateTemplateProductDao implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaoException {
		template.persist(product);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		template.merge(product);
	}

	@Override
	public Product getProduct(Integer productID) throws DaoException {
	return template.get(Product.class, productID);
	}

	@Override
	public void deleteProduct(Integer productID) throws DaoException {
		Product p=getProduct(productID);
		p.setDiscontinued(1);
		template.merge(p);
	}

	
	@Override
	public List<Product> getAllProducts() throws DaoException {
    DetachedCriteria dc=DetachedCriteria.forClass(Product.class);
    return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getAllProductsByPriceRange(int min, int max) throws DaoException {
		DetachedCriteria dc=DetachedCriteria.forClass(Product.class);
		   dc.add(Restrictions.between("product_price",min,max));
		    return (List<Product>) template.findByCriteria(dc);	}

	@Override
	public long count() throws DaoException {
		DetachedCriteria dc=DetachedCriteria.forClass(Product.class);
		dc.setProjection(Projections.rowCount());
		return (long) template.findByCriteria(dc).get(0);
	}

}

