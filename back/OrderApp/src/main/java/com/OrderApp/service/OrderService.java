package com.OrderApp.service;

import com.OrderApp.dto.OrderItemResponse;
import com.OrderApp.dto.OrderResponse;
import com.OrderApp.exception.businessException.OrderNotFound;
import com.OrderApp.model.Order;
import com.OrderApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse getOrderById(UUID id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("Order not found: " + id));

        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(orderItem ->
                        new OrderItemResponse(
                        orderItem.getItem().getId(),
                        orderItem.getItem().getName(),
                        orderItem.getQuantity(),
                        orderItem.getPriceAtOrderTime()
                )).toList();

        return new OrderResponse(
                order.getId(),
                itemResponses,
                order.getCustomerName(),
                order.getDeliveryAddress()
        );
    }
}
