package com.OrderApp.controller;

import com.OrderApp.dto.OrderRequest;
import com.OrderApp.dto.OrderResponse;
import com.OrderApp.model.Order;
import com.OrderApp.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest req){
        OrderResponse res = orderService.createOrder(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id){
        OrderResponse res = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> res = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
