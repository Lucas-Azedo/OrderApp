package com.OrderApp.service;

import com.OrderApp.dto.OrderItemResponse;
import com.OrderApp.dto.OrderRequest;
import com.OrderApp.dto.OrderResponse;
import com.OrderApp.exception.businessException.OrderNotFound;
import com.OrderApp.model.Item;
import com.OrderApp.model.Order;
import com.OrderApp.model.OrderItem;
import com.OrderApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public OrderResponse createOrder(OrderRequest req){
        Order order = new Order();
        order.setCustomerName(req.getCustomerName());
        order.setDeliveryAddress(req.getDeliveryAddress());

        List<OrderItem> orderItems = req.getItems().stream().map(itemRequest -> {
            OrderItem orderItem = new OrderItem();

            Item item = itemService.getItemEntityById(itemRequest.getItemId());

            orderItem.setItem(item);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrderTime(itemRequest.getPriceAtOrderTime());
            orderItem.setOrder(order);

            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);

        orderRepository.save(order);

        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemResponse(
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
    
    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().
                map(order -> new OrderResponse(
                        order.getId(),
                        order.getOrderItems().stream()
                                        .map(orderItem -> new OrderItemResponse(
                                                orderItem.getItem().getId(),
                                                orderItem.getItem().getName(),
                                                orderItem.getQuantity(),
                                                orderItem.getPriceAtOrderTime()
                                        )).toList(),
                        order.getCustomerName(),
                        order.getDeliveryAddress()
                )).toList();
    }

    public void deleteOrderById(UUID id){
        orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("Order not found: " + id));

        orderRepository.deleteById(id);
    }

    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }
}
