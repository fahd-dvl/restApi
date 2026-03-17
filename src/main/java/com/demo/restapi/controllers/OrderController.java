package com.demo.restapi.controllers;

import com.demo.restapi.entities.Order;
import com.demo.restapi.entities.OrderStatus;
import com.demo.restapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 1. Get List
    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    // 2. Get by Product ID
    @GetMapping("/product/{productId}")
    public List<Order> getByProduct(@PathVariable String productId) {
        return orderService.getOrdersByProductId(productId);
    }
    // 3. Get by Customer Name
    @GetMapping("/customer/{name}")
    public List<Order> getByCustomer(@PathVariable String name) {
        return orderService.getOrdersByCustomer(name);
    }

    // 4. Create Order
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PatchMapping("/{id}")
    public Order updateStatus(@PathVariable String id, @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }

    // 6. Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        orderService.deleteOrder(id);
    }

}
