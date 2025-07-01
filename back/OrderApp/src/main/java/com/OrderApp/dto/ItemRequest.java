package com.OrderApp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    @NotNull(message="Name is required")
    String name;

    @NotNull(message="Description is required")
    String description;

}
