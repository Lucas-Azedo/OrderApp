package com.OrderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private BigDecimal orderAmount;
}
