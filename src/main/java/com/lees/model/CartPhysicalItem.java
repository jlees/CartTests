package com.lees.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class CartPhysicalItem {
	
    @Size(max = 200)
    @NotNull
    private String recipientAddress1;
    
    @Size(max = 200)
    private String recipientAddress2;

    @Size(max = 100)
    @NotNull
    private String recipientCity;

    @Size(max = 50)
    @NotNull
    private String recipientState;    
    
    @Size(max = 10)
    @NotNull
    private String recipientZipcode;    		
	
}
