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
public class CartPersonalizedItem {
	
    @Size(max = 50)
    @NotNull
    private String keyUID;    

    @Size(max = 10)
    @NotNull
    private String sku;    

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CartPhysicalItem> items = new HashSet<CartPhysicalItem>();

    public void addItem(CartPhysicalItem item){
    	if (item != null){
    		items.add(item);
    	}
    }

    public void removeItem(CartPhysicalItem item){
    	if (item != null){
    		items.remove(item);
    	}
    }
    
	public void setItems(Set<CartPhysicalItem> items) {
        this.items = items;
    }

	public Set<CartPhysicalItem> getItems() {
        return Collections.unmodifiableSet( this.items );
    }
}
