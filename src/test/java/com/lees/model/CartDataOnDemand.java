package com.lees.model;

import com.lees.repository.CartRepository;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Cart.class)
public class CartDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Cart> data;

	@Autowired
    CartRepository cartRepository;

	public Cart getNewTransientCart(int index) {
        Cart obj = new Cart();
        setShippingAddress1(obj, index);
        setShippingAddress2(obj, index);
        setShippingCity(obj, index);
        setShippingState(obj, index);
        setShippingZipcode(obj, index);
        return obj;
    }

	public void setShippingAddress1(Cart obj, int index) {
        String shippingAddress1 = "shippingAddress1_" + index;
        if (shippingAddress1.length() > 200) {
            shippingAddress1 = shippingAddress1.substring(0, 200);
        }
        obj.setShippingAddress1(shippingAddress1);
    }

	public void setShippingAddress2(Cart obj, int index) {
        String shippingAddress2 = "shippingAddress2_" + index;
        if (shippingAddress2.length() > 200) {
            shippingAddress2 = shippingAddress2.substring(0, 200);
        }
        obj.setShippingAddress2(shippingAddress2);
    }

	public void setShippingCity(Cart obj, int index) {
        String shippingCity = "shippingCity_" + index;
        if (shippingCity.length() > 100) {
            shippingCity = shippingCity.substring(0, 100);
        }
        obj.setShippingCity(shippingCity);
    }

	public void setShippingState(Cart obj, int index) {
        String shippingState = "shippingState_" + index;
        if (shippingState.length() > 50) {
            shippingState = shippingState.substring(0, 50);
        }
        obj.setShippingState(shippingState);
    }

	public void setShippingZipcode(Cart obj, int index) {
        String shippingZipcode = "shipping_" + index;
        if (shippingZipcode.length() > 10) {
            shippingZipcode = shippingZipcode.substring(0, 10);
        }
        obj.setShippingZipcode(shippingZipcode);
    }

	public Cart getSpecificCart(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Cart obj = data.get(index);
        Long id = obj.getId();
        return cartRepository.findOne(id);
    }

	public Cart getRandomCart() {
        init();
        Cart obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return cartRepository.findOne(id);
    }

	public boolean modifyCart(Cart obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = cartRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Cart' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Cart>();
        for (int i = 0; i < 10; i++) {
            Cart obj = getNewTransientCart(i);
            try {
                cartRepository.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            cartRepository.flush();
            data.add(obj);
        }
    }
}
