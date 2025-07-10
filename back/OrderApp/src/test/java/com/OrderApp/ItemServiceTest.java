package com.OrderApp;

import com.OrderApp.dto.ItemRequest;
import com.OrderApp.dto.ItemResponse;
import com.OrderApp.exception.businessException.ItemNotFound;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import com.OrderApp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    public void testUpdateItem_ShouldThrowException_WhenItemNotFound() {
        UUID id = UUID.randomUUID();
        ItemRequest req = new ItemRequest("Name", "Desc", new BigDecimal("19.90"));

        when(itemRepository.findById(id))
                .thenReturn(Optional.empty());

        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> {
            itemService.updateItem(id, req);
        });

        assertTrue(exception.getMessage().contains("Item not found: " + id));
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

        when(itemRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act & Assert
        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> {
            itemService.getItemById(id);
        });

        // Assert
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Item not found: " + id));

        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    public void getAllItems_ShouldReturnListOfItemResponses() {
        // Arrange
        List<Item> items = List.of(
                Item.builder().id(UUID.randomUUID()).name("Item1").description("Desc1").price(new BigDecimal("10.00")).build(),
                Item.builder().id(UUID.randomUUID()).name("Item2").description("Desc2").price(new BigDecimal("20.00")).build()
        );

        when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<ItemResponse> responses = itemService.getAllItems();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("Item1", responses.get(0).getName());
        assertEquals("Item2", responses.get(1).getName());
    }

    @Test
    public void testGetAllItems_ShouldReturnEmptyList_WhenNoItemsExist() {
        //Arrange
        when(itemRepository.findAll()).thenReturn(new ArrayList<>());

        //Act
        List<ItemResponse> responses = itemService.getAllItems();

        //Assert
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }

    @Test
    public void testGetItemEntityById_ShouldReturnItem_WhenItemExists() {
        UUID id = UUID.randomUUID();
        Item item = Item.builder().id(id).name("Item").description("Desc").price(new BigDecimal("15.00")).build();

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item result = itemService.getItemEntityById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Item", result.getName());
    }

    @Test
    public void testDeleteItem_ShouldDeleteItem_WhenItemExists(){
        //Arrange
        UUID id = UUID.randomUUID();
        BigDecimal price = new BigDecimal("39.90");

        Item item = Item.builder()
                .id(id)
                .name("Name")
                .description("Desc")
                .price(price)
                .build();

        when(itemRepository.findById(id))
                .thenReturn(Optional.of(item));

        doNothing().when(itemRepository).deleteById(id);

        //Act
        itemService.deleteItem(id);

        //Assert
        verify(itemRepository, times(1)).findById(id);
        verify(itemRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteItem_ShouldThrowException_WhenItemNotFound(){
        // Arrange
        UUID id = UUID.randomUUID();

        //Act & Assert
        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> {
            itemService.deleteItem(id);
        });


        assertTrue(exception.getMessage().contains("Item not found: " + id));
    }





}

