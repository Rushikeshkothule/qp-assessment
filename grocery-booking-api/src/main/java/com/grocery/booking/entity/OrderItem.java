package com.grocery.booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    public Long getId() {
		return id;
	}
	public GroceryItem getGroceryItem() {
		return groceryItem;
	}
	public void setGroceryItem(GroceryItem groceryItem) {
		this.groceryItem = groceryItem;
	}
	public GroceryOrder getOrder() {
		return order;
	}
	public void setOrder(GroceryOrder order) {
		this.order = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private GroceryItem groceryItem;
    @ManyToOne
    @JsonBackReference
    private GroceryOrder order;
    private int quantity;
}
