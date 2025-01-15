package com.grocery.booking.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class GroceryItem {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getInventoryLevel() {
	return inventoryLevel;
}
public void setInventoryLevel(int inventoryLevel) {
	this.inventoryLevel = inventoryLevel;
}
private String name;
 private double price;
 private int inventoryLevel;
 
 @OneToMany(mappedBy = "groceryItem")
 @OnDelete(action = OnDeleteAction.CASCADE)
 private List<OrderItem> orderItems;
}

