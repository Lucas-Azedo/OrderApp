package com.OrderApp.service;

import com.OrderApp.dto.ItemResponse;
import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    ItemRepository itemRepository;

    public ItemResponse createItem(Item req){

        itemRepository.save(req);

        return new ItemResponse(req.getName(), req.getDescription());
    }
}
