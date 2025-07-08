package com.OrderApp;

import com.OrderApp.dto.ItemRequest;
import com.OrderApp.dto.ItemResponse;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import com.OrderApp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    public void testCreateItem_ShouldReturnItemResponse() {
        // Arrange
        BigDecimal price = new BigDecimal(40);
        UUID id = UUID.randomUUID();

        ItemRequest req = new ItemRequest("Item", "Desc", price);

        Item savedItem = Item.builder()
                .id(id)
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .build();

        when(itemRepository.save(any(Item.class)))
                .thenReturn(savedItem);

        // Act
        ItemResponse res = itemService.createItem(req);

        // Assert
        assertNotNull(res);
        assertEquals(id, res.getId());
        assertEquals("Item", res.getName());
        assertEquals("Desc", res.getDescription());
        assertEquals(price, res.getPrice());

        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    public void testUpdateItem_ShouldUpdateReturnItemResponse(){
        // Arrange
        UUID id = UUID.randomUUID();
        BigDecimal price = new BigDecimal("29.90");

        Item existingItem = Item.builder()
                .id(id)
                .name("Old name")
                .description("Old desc")
                .price(price)
                .build();

        ItemRequest req = new ItemRequest("New name", "New desc", price);

        when(itemRepository.findById(id))
                .thenReturn(Optional.of(existingItem));

        when(itemRepository.save(existingItem)).
                thenReturn(existingItem);

        // Act
        ItemResponse res = itemService.updateItem(id, req);

        // Assert
        assertNotNull(res);
        assertEquals(id, res.getId());
        assertEquals("New name", res.getName());
        assertEquals("New desc", res.getDescription());
        assertEquals(price, res.getPrice());

        verify(itemRepository, times(1)).findById(id);
        verify(itemRepository, times(1)).save(existingItem);
    }
}

