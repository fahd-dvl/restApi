package com.demo.restapi.controllers;

import com.demo.restapi.entities.Order;
import com.demo.restapi.entities.OrderStatus;
import com.demo.restapi.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderService orderService;

    // 1. Get List
    @GetMapping(headers = "X-API-VERSION=1")
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    // 2. Get by Product ID
    @GetMapping(value = "/product/{productId}", headers = "X-API-VERSION=1")
    public List<Order> getByProduct(@PathVariable String productId) {
        return orderService.getOrdersByProductId(productId);
    }
    // 3. Get by Customer Name
    @GetMapping(value = "/customer/{name}",headers = "X-API-VERSION=1" )

    public List<Order> getByCustomer(@PathVariable String name) {
        return orderService.getOrdersByCustomer(name);
    }

    // 4. Create Order
    @PostMapping(headers = "X-API-VERSION=1")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PatchMapping(value = "/{id}", headers = "X-API-VERSION=1")

    public Order updateStatus(@PathVariable String id, @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }

    // 6. Delete
    @DeleteMapping(value = "/{id}", headers = "X-API-VERSION=1")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        orderService.deleteOrder(id);
    }

}
