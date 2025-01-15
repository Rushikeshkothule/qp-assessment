package com.grocery.booking.service;

import com.grocery.booking.dto.OrderItemRequest;
import com.grocery.booking.dto.OrderRequest;
import com.grocery.booking.dto.UserDTO;
import com.grocery.booking.entity.*;
import com.grocery.booking.repository.GroceryItemRepository;
import com.grocery.booking.repository.GroceryOrderRepository;

import com.grocery.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

 @Autowired
 private GroceryItemRepository groceryItemRepository;

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private GroceryOrderRepository orderRepository;

 // Fetch all available grocery items (where inventoryLevel > 0)
 public List<GroceryItem> getAvailableGroceryItems() {
     return groceryItemRepository.findByInventoryLevelGreaterThan(0);
 }

 // Place an order for multiple grocery items
 public GroceryOrder placeOrder(OrderRequest orderRequest) {
	    // Log the order request
	    System.out.println("Order Request: " + orderRequest);

	    // Validate the userId
	    if (orderRequest.getUserId() == null) {
	        throw new IllegalArgumentException("User ID must not be null");
	    }

	    // Log the userId
	    System.out.println("User ID: " + orderRequest.getUserId());

	    // Find the user by ID
	    AppUser user = userRepository.findById(orderRequest.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + orderRequest.getUserId()));

	    // Log the user
	    System.out.println("User: " + user);

	    // Create a new order
	    GroceryOrder order = new GroceryOrder();
	    order.setUser(user);
	    order.setOrderDate(LocalDateTime.now());

	    // Process each item in the order
	    List<OrderItem> orderItems = new ArrayList<>();
	    for (OrderItemRequest itemRequest : orderRequest.getItems()) {
	        // Log the groceryItemId
	        System.out.println("Grocery Item ID: " + itemRequest.getGroceryItemId());

	        // Find the grocery item by ID
	        GroceryItem groceryItem = groceryItemRepository.findById(itemRequest.getGroceryItemId())
	                .orElseThrow(() -> new RuntimeException("Grocery item not found with id: " + itemRequest.getGroceryItemId()));

	        // Log the grocery item
	        System.out.println("Grocery Item: " + groceryItem);

	        // Check if the requested quantity is available
	        if (groceryItem.getInventoryLevel() < itemRequest.getQuantity()) {
	            throw new RuntimeException("Insufficient inventory for item: " + groceryItem.getName());
	        }

	        // Create an order item
	        OrderItem orderItem = new OrderItem();
	        orderItem.setGroceryItem(groceryItem);
	        orderItem.setOrder(order);
	        orderItem.setQuantity(itemRequest.getQuantity());
	        orderItems.add(orderItem);

	        // Update the inventory level
	        groceryItem.setInventoryLevel(groceryItem.getInventoryLevel() - itemRequest.getQuantity());
	        groceryItemRepository.save(groceryItem);
	    }

	    // Set the order items and save the order
	    order.setOrderItems(orderItems);
	    return orderRepository.save(order);
	}

public AppUser createUser(UserDTO userDTO) {
	AppUser user = new AppUser();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    return userRepository.save(user);
}
}
