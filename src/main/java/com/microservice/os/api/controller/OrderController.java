package com.microservice.os.api.controller;

import com.microservice.os.api.dto.Payment;
import com.microservice.os.api.dto.TransactionRequest;
import com.microservice.os.api.dto.TransactionResponse;
import com.microservice.os.api.entity.Order;
import com.microservice.os.api.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }

    @GetMapping("/allOrder")
    public List<Order> allOrders(){
        return orderService.allOrders();
    }
}
