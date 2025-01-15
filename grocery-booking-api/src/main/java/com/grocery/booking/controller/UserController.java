// com.grocery.booking.controller.UserController
package com.grocery.booking.controller;

import com.grocery.booking.dto.OrderRequest;
import com.grocery.booking.dto.UserDTO;
import com.grocery.booking.entity.AppUser;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.entity.GroceryOrder;
import com.grocery.booking.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody UserDTO userDTO) {
        AppUser user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/grocery-items")
    public ResponseEntity<List<GroceryItem>> getAvailableGroceryItems() {
        return ResponseEntity.ok(userService.getAvailableGroceryItems());
    }

    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        // Log the order request
        System.out.println("Received Order Request: " + orderRequest);

        try {
            GroceryOrder order = userService.placeOrder(orderRequest);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}