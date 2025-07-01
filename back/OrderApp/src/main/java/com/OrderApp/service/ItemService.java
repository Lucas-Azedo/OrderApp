package com.OrderApp.service;

import com.OrderApp.dto.ItemResponse;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponse createItem(Item req){

        itemRepository.save(req);

        return new ItemResponse(req.getName(), req.getDescription());
    }
}
