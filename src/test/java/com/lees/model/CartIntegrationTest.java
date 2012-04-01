package com.lees.model;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Cart.class)
public class CartIntegrationTest {

    @Test
    public void testMarkerMethod() {
    	
    	Cart cart = new Cart();
    	cart.setShippingAddress1("300 Market Ave");
    	cart.setShippingAddress2("");
    	cart.setShippingCity("Warren");
    	cart.setShippingState("OH");
    	cart.setShippingZipcode("44484");
    	
    	CartPersonalizedItem cartPersonalizedItem = new CartPersonalizedItem();
    	cartPersonalizedItem.setKeyUID("1234567");
    	cartPersonalizedItem.setSku("ABC123");
    	
    	CartPhysicalItem cartPhysicalItem = new CartPhysicalItem();
    	cartPhysicalItem.setRecipientAddress1("100 Test Ave");
    	cartPhysicalItem.setRecipientAddress2("");
    	cartPhysicalItem.setRecipientCity("Akron");
    	cartPhysicalItem.setRecipientState("OH");
    	cartPhysicalItem.setRecipientZipcode("44310");
    	cartPersonalizedItem.addItem(cartPhysicalItem);

    	cartPhysicalItem = new CartPhysicalItem();
    	cartPhysicalItem.setRecipientAddress1("500 Main St");
    	cartPhysicalItem.setRecipientAddress2("");
    	cartPhysicalItem.setRecipientCity("Brunswick");
    	cartPhysicalItem.setRecipientState("OH");
    	cartPhysicalItem.setRecipientZipcode("44212");
    	cartPersonalizedItem.addItem(cartPhysicalItem);
    	
    	cart.addItem(cartPersonalizedItem);
    	
		cart = cartRepository.save(cart);
		cartRepository.flush();
		
    	String city = "Brunswick";
    	List<CartPhysicalItem> matchingCartPhysicalItems = cartRepository.findCartPhysicalItemsByRecipientCity(city);
    	Assert.assertNotNull(matchingCartPhysicalItems);
    	Assert.assertEquals(1, matchingCartPhysicalItems.size());
		
    	List<Cart> matchingCarts = cartRepository.findCartByRecipientCity(city);
    	Assert.assertNotNull(matchingCarts);
    	Assert.assertEquals(1, matchingCarts.size());
    	
    	String shippingAddress1 = "Market";
    	matchingCarts = cartRepository.findCartsByShippingAddress1Like(shippingAddress1);
    	Assert.assertNotNull(matchingCarts);
    	Assert.assertEquals(1, matchingCarts.size());

    	String state = "OH";
    	matchingCartPhysicalItems = cartRepository.findCartPhysicalItemsByRecipientState(state);
    	Assert.assertNotNull(matchingCartPhysicalItems);
    	Assert.assertEquals(2, matchingCartPhysicalItems.size());
		
    	matchingCarts = cartRepository.findCartByRecipientState(state);
    	Assert.assertNotNull(matchingCarts);
    	Assert.assertEquals(1, matchingCarts.size());
    	
		Long cartId = cart.getId();
		
		Cart matchingCart = cartRepository.findOne(cartId);
    	Assert.assertNotNull(matchingCart);
    	
    	Set<CartPersonalizedItem> cartPersonalizedItems = matchingCart.getItems();
    	Assert.assertNotNull(cartPersonalizedItems);
    	Assert.assertEquals(1, cartPersonalizedItems.size());
    	
    	CartPersonalizedItem matchingCartPersonalizedItem = cartPersonalizedItems.iterator().next();
    	Assert.assertNotNull(matchingCartPersonalizedItem);
    	Set<CartPhysicalItem> cartPhysicalItems = matchingCartPersonalizedItem.getItems();
    	Assert.assertNotNull(cartPhysicalItems);
    	Assert.assertEquals(2, cartPhysicalItems.size());
    			
    }
}
