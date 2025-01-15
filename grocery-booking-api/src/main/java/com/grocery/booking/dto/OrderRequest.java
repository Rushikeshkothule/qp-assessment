package com.grocery.booking.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
@NotNull(message = "User ID must not be null")
 private Long userId;
 public void setUserId(Long userId) {
	this.userId = userId;
}
public Long getUserId() {
	return userId;
}

private List<OrderItemRequest> items;

public List<OrderItemRequest> getItems() {
	return items;
}
public void setItems(List<OrderItemRequest> items) {
	this.items = items;
}


@Override
public String toString() {
    return "OrderRequest{" +
            "userId=" + userId +
            ", items=" + items +
            '}';
}
}
