package com.grocery.booking.service;

import com.grocery.booking.dto.GroceryItemDTO;
import com.grocery.booking.entity.GroceryItem;
import com.grocery.booking.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroceryItemService {

 @Autowired
 private GroceryItemRepository groceryItemRepository;

 public GroceryItem addGroceryItem(GroceryItemDTO groceryItemDTO) {
     GroceryItem groceryItem = new GroceryItem();
     groceryItem.setName(groceryItemDTO.getName());
     groceryItem.setPrice(groceryItemDTO.getPrice());
     groceryItem.setInventoryLevel(groceryItemDTO.getInventoryLevel());
     return groceryItemRepository.save(groceryItem);
 }

 public List<GroceryItem> getAllGroceryItems() {
     return groceryItemRepository.findAll();
 }

 public GroceryItem updateGroceryItem(Long id, GroceryItemDTO groceryItemDTO) {
     GroceryItem groceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
     groceryItem.setName(groceryItemDTO.getName());
     groceryItem.setPrice(groceryItemDTO.getPrice());
     groceryItem.setInventoryLevel(groceryItemDTO.getInventoryLevel());
     return groceryItemRepository.save(groceryItem);
 }

 public void deleteGroceryItem(Long id) {
     groceryItemRepository.deleteById(id);
 }

 public GroceryItem updateInventory(Long id, int inventoryLevel) {
     GroceryItem groceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
     groceryItem.setInventoryLevel(inventoryLevel);
     return groceryItemRepository.save(groceryItem);
 }
}