package com.lees.repository;

import java.util.List;

import com.lees.model.Cart;

public interface CartRepositoryCustom {

	public List<Cart> findCartsByShippingAddress1Like(String shippingAddress1);
	
}
