package com.eduardoportfolio.store.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
//Indicate to Spring to modify the way that it creates objects, using this, we can add a smaller-scoped
//objects into a larger-scoped objects, in this case Session scope into Application scope. Doing this, we
//can leave the ShoppingCartController class without the Scope_Request.
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ShoppingCart {

	private Map<ShoppingItem, Integer> items = new LinkedHashMap<ShoppingItem, Integer>();

	public void add(ShoppingItem item) {
		items.put(item, getQuantity(item) + 1);
	}

	public Integer getQuantity(ShoppingItem item) {
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}

	public Integer getQuantity() {
		return items.values().stream()
				.reduce(0, (next, accumulator) -> next + accumulator);
	}

	public Collection<ShoppingItem> getList() {
		return items.keySet();
	}

	public BigDecimal getTotal(ShoppingItem item) {
		return item.getTotal(getQuantity(item));
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		//TODO change to reduce?
		for(ShoppingItem item : items.keySet()){
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remove(ShoppingItem shoppingItem) {
		items.remove(shoppingItem);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

}