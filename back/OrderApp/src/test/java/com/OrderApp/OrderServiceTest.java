package com.OrderApp;

import com.OrderApp.dto.OrderItemRequest;
import com.OrderApp.dto.OrderItemResponse;
import com.OrderApp.dto.OrderRequest;
import com.OrderApp.dto.OrderResponse;
import com.OrderApp.model.Item;
import com.OrderApp.model.Order;
import com.OrderApp.repository.ItemRepository;
import com.OrderApp.repository.OrderRepository;
import com.OrderApp.service.ItemService;
import com.OrderApp.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    ItemService itemService;

    @InjectMocks
    OrderService orderService;

    @Test
    public void testCreateOrder_ShouldReturnOrderResponse() {
        // Arrange
        UUID itemId = UUID.randomUUID();
        String customerName = "João";
        String address = "Rua A, 123";
        int quantity = 2;
        BigDecimal unitPrice = new BigDecimal("10.00");
        BigDecimal expectedTotalPrice = new BigDecimal("20.00");

        Item item = Item.builder()
                .id(itemId)
                .name("Produto A")
                .price(unitPrice)
                .build();

        OrderItemRequest itemRequest = new OrderItemRequest(itemId, quantity);
        OrderRequest orderRequest = new OrderRequest(List.of(itemRequest), customerName, address);

        when(itemService.getItemEntityById(itemId))
                .thenReturn(item);

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> {
                    Order order = invocation.getArgument(0);
                    order.setId(UUID.randomUUID());
                    return order;
        });

        // Act
        OrderResponse response = orderService.createOrder(orderRequest);

        // Assert
        assertNotNull(response);
        assertEquals(customerName, response.getCustomerName());
        assertEquals(address, response.getDeliveryAddress());
        assertEquals(new BigDecimal("20.00"), response.getOrderAmount());

        assertEquals(1, response.getItems().size());
        OrderItemResponse itemRes = response.getItems().getFirst();

        assertEquals(itemId, itemRes.getItemId());
        assertEquals("Produto A", itemRes.getItemName());
        assertEquals(2, itemRes.getQuantity());
        assertEquals(new BigDecimal("20.00"), itemRes.getPriceAtOrderTime());

        verify(itemService, times(1)).getItemEntityById(itemId);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

}
