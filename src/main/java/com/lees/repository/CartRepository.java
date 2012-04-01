package com.lees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

import com.lees.model.Cart;
import com.lees.model.CartPhysicalItem;

@Repository
@RooJpaRepository(domainType = Cart.class)
public interface CartRepository extends JpaSpecificationExecutor<Cart>, JpaRepository<Cart, Long> {
	
	@Query("SELECT DISTINCT cart FROM Cart cart join fetch cart.items as cartPersonalizedItem join fetch cartPersonalizedItem.items as cartPhysicalItem WHERE cartPhysicalItem.recipientCity = ?1")
	public List<Cart> findCartByRecipientCity(String recipientCity);	

	
	@Query("SELECT cartPhysicalItem FROM CartPhysicalItem as cartPhysicalItem WHERE cartPhysicalItem.recipientCity = ?1")
	public List<CartPhysicalItem> findCartPhysicalItemsByRecipientCity(String recipientCity);	

	@Query("SELECT DISTINCT cart FROM Cart cart join fetch cart.items as cartPersonalizedItem join fetch cartPersonalizedItem.items as cartPhysicalItem WHERE cartPhysicalItem.recipientState = ?1")
	public List<Cart> findCartByRecipientState(String recipientState);	

	
	@Query("SELECT cartPhysicalItem FROM CartPhysicalItem as cartPhysicalItem WHERE cartPhysicalItem.recipientState = ?1")
	public List<CartPhysicalItem> findCartPhysicalItemsByRecipientState(String recipientState);		
	
}
