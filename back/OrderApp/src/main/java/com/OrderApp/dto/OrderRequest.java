package com.OrderApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @Min(value = 1, message = "Adicione pelo menos um item")
    private List<OrderItemResponse> items;

    @NotBlank(message = "Nome é obrigatório")
    private String customerName;

    @NotBlank(message = "Endereço é obrigatório")
    private String deliveryAddress;
}
