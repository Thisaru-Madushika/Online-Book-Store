package com.example.product_order_service.controller;

import com.example.product_order_service.model.Order;
import com.example.product_order_service.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setStatus("CONFIRMED"); // Assignment එකේ හැටියට Status එක අනිවාර්යයෙන්ම CONFIRMED වෙන්න ඕනේ
        return orderRepository.save(order);
    }
}