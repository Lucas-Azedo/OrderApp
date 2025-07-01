package com.OrderApp.service;

import com.OrderApp.dto.ItemRequest;
import com.OrderApp.dto.ItemResponse;
import com.OrderApp.exception.businessException.ItemNotFound;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponse createItem(ItemRequest req){

        Item item = Item.builder()
                .name(req.getName())
                .description(req.getDescription())
                .build();


        item = itemRepository.save(item);

        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription()
        );
    }

    public ItemResponse getItemById(UUID id){

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFound("Item not found" + id));

        return new ItemResponse(item.getId(), item.getName(), item.getDescription());
    }
}
