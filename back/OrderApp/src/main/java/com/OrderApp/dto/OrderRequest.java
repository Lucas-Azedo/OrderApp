package com.OrderApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "Adicione pelo menos um item")
    @Valid
    private List<OrderItemRequest> orderItems;

    @NotBlank(message = "Nome é obrigatório")
    private String customerName;

    @NotBlank(message = "Endereço é obrigatório")
    private String deliveryAddress;
}
