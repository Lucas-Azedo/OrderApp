package com.OrderApp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    @NotNull(message="Name is required")
    private String name;

    @NotNull(message="Description is required")
    private String description;

    @NotNull(message="Price is required")
    private BigDecimal price;

}
