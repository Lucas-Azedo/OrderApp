package com.OrderApp.service;

import com.OrderApp.model.Item;
import com.OrderApp.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    ItemRepository itemRepository;

    public void createItem(Item item){
        itemRepository.save(item);
    }
}
