package com.OrderApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "Id é obrigatório")
    private UUID itemId;

    @Min(value = 1, message = "Quantidade deve ser ao menos 1")
    private int quantity;
}
