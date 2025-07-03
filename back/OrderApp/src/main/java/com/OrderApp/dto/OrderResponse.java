package com.OrderApp.dto;

import com.OrderApp.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID id;

    private List<OrderItemResponse> items;

    private String customerName;

    private String deliveryAddress;
}
