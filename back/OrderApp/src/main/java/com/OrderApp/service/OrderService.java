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

import java.math.BigDecimal;
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

        List<OrderItem> orderItems = req.getOrderItems().stream().map(itemRequest -> {
            OrderItem orderItem = new OrderItem();

            Item item = itemService.getItemEntityById(itemRequest.getItemId());

            int quantity = itemRequest.getQuantity();

            BigDecimal price = item.getPrice().multiply((BigDecimal.valueOf(quantity)));

            orderItem.setItem(item);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrderTime(price);
            orderItem.setOrder(order);

            return orderItem;
        }).toList();

        BigDecimal orderAmount = orderItems.stream()
                .map(OrderItem::getPriceAtOrderTime)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount);

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
                order.getDeliveryAddress(),
                order.getOrderAmount()
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
                order.getDeliveryAddress(),
                order.getOrderAmount()
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
                        order.getDeliveryAddress(),
                        order.getOrderAmount()
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
