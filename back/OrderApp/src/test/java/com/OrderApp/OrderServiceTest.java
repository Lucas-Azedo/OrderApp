package com.OrderApp;

import com.OrderApp.repository.ItemRepository;
import com.OrderApp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    public void testCreateOrder_ShouldReturnOrderResponse(){

    }
}
