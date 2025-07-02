package com.OrderApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    UUID id;

    String name;

    String description;

    BigDecimal price;
}
