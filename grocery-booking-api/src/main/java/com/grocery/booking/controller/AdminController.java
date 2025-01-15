package com.grocery.booking.controller;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/grocery-items")
public class AdminController {

 @Autowired
 private GroceryItemService groceryItemService;

 @PostMapping
 public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItemDTO groceryItemDTO) {
     return ResponseEntity.ok(groceryItemService.addGroceryItem(groceryItemDTO));
 }

 @GetMapping
 public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
     return ResponseEntity.ok(groceryItemService.getAllGroceryItems());
 }

 @PutMapping("/{id}")
 public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItemDTO groceryItemDTO) {
     return ResponseEntity.ok(groceryItemService.updateGroceryItem(id, groceryItemDTO));
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
     groceryItemService.deleteGroceryItem(id);
     return ResponseEntity.noContent().build();
 }

 @PatchMapping("/{id}/inventory")
 public ResponseEntity<GroceryItem> updateInventory(@PathVariable Long id, @RequestParam int inventoryLevel) {
     return ResponseEntity.ok(groceryItemService.updateInventory(id, inventoryLevel));
 }
}