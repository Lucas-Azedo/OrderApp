package com.OrderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private UUID itemId;

    private String itemName;

    private int quantity;

    private BigDecimal priceAtOrderTime;
}

