package com.OrderApp.controller;

import com.OrderApp.dto.ItemRequest;
import com.OrderApp.dto.ItemResponse;
import com.OrderApp.service.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody @Valid ItemRequest req){
        ItemResponse res = itemService.createItem(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable UUID id){
        ItemResponse res = itemService.getItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems(){
        List<ItemResponse> res = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
