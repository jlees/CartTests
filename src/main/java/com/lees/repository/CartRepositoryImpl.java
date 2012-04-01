package com.lees.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lees.model.Cart;

@SuppressWarnings("unchecked")
public class CartRepositoryImpl implements CartRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<Cart> findCartsByShippingAddress1Like(String shippingAddress1) {
		shippingAddress1 = createLikeExpression("shippingAddress1", shippingAddress1);
		Query query = entityManager.createQuery("SELECT cart FROM Cart cart WHERE LOWER(cart.shippingAddress1) like LOWER(:shippingAddress1)");
		query.setParameter("shippingAddress1", shippingAddress1);
		return query.getResultList();
	}
	
	private String createLikeExpression(String propertyName, String propertyValue){
        if (propertyValue == null || propertyValue.length() == 0){
        	String msg = String.format("The %s argument is required", propertyName);
        	throw new IllegalArgumentException(msg);
        }
        propertyValue = propertyValue.replace('*', '%');
        if (propertyValue.charAt(0) != '%') {
            propertyValue = "%" + propertyValue;
        }
        if (propertyValue.charAt(propertyValue.length() - 1) != '%') {
            propertyValue = propertyValue + "%";
        }
		return propertyValue;
	}

}
