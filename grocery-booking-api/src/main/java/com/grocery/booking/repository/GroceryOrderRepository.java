package com.grocery.booking.repository;

import com.grocery.booking.entity.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryOrderRepository extends JpaRepository<GroceryOrder, Long> {
}
