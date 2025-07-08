package com.OrderApp;

import com.OrderApp.dto.ItemRequest;
import com.OrderApp.dto.ItemResponse;
import com.OrderApp.exception.businessException.ItemNotFound;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import com.OrderApp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    public void testCreateItem_ShouldReturnItemResponse() {
        // Arrange
        BigDecimal price = new BigDecimal("19.90");
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

    @Test
    public void testGetItemById_ShouldReturnItemResponse_WhenItemExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        BigDecimal price = new BigDecimal("39.90");

        Item item = Item.builder()
                .id(id)
                .name("Name")
                .description("Desc")
                .price(price)
                .build();

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        // Act
        ItemResponse response = itemService.getItemById(id);

        // Assert
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Name", response.getName());
        assertEquals("Desc", response.getDescription());
        assertEquals(price, response.getPrice());

        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    public void testGetItemById_ShouldThrowException_WhenItemNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ItemNotFound e = null;
        try {
            itemService.getItemById(id);
            fail("Expected ItemNotFound exception");
        } catch (ItemNotFound ex) {
            e = ex;
        }

        // Assert
        assertNotNull(e);
        assertTrue(e.getMessage().contains("Item not found: " + id));

        verify(itemRepository, times(1)).findById(id);
    }


}

