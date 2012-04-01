package com.lees.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Cart {

    @Size(max = 200)
    @NotNull
    private String shippingAddress1;

    @Size(max = 200)
    private String shippingAddress2;

    @Size(max = 100)
    @NotNull
    private String shippingCity;

    @Size(max = 50)
    @NotNull
    private String shippingState;

    @Size(max = 10)
    @NotNull
    private String shippingZipcode;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CartPersonalizedItem> items = new HashSet<CartPersonalizedItem>();

    public void addItem(CartPersonalizedItem item){
    	if (item != null){
    		items.add(item);
    	}
    }

    public void removeItem(CartPersonalizedItem item){
    	if (item != null){
    		items.remove(item);
    	}
    }
    
	public Set<CartPersonalizedItem> getItems() {
        return Collections.unmodifiableSet( this.items );
    }
    
	public void setItems(Set<CartPersonalizedItem> items) {
        this.items = items;
    }

}
