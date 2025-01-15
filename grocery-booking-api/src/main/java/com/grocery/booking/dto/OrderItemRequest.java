package com.grocery.booking.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class OrderItemRequest {
    @NotNull(message = "Grocery Item ID must not be null")
    private Long groceryItemId;

    @NotNull(message = "Quantity must not be null")
    private Integer quantity;

    public Long getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(Long groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemRequest{" +
                "groceryItemId=" + groceryItemId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemRequest that = (OrderItemRequest) o;
        return Objects.equals(groceryItemId, that.groceryItemId) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groceryItemId, quantity);
    }
}