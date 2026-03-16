package com.demo.restapi.services;

import com.demo.restapi.entities.Order;
import com.demo.restapi.entities.OrderStatus;
import com.demo.restapi.entities.Product;
import com.demo.restapi.repositories.OrderRepository;
import com.demo.restapi.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 1. Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 2. Get by Product
    public List<Order> getOrdersByProductId(String productId) {
        return orderRepository.findByProducts_Id(productId);
    }

    // 3. Get by Customer
    public List<Order> getOrdersByCustomer(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }

    // 4. Create (With Price Calculation)
    @Transactional
    public Order createOrder(Order order) {
        // 1. Fetch all products in the order from the DB to get real prices
        double total = 0.0;
        List<Product> managedProducts = new java.util.ArrayList<>();

        for (Product p : order.getProducts()) {
            Product managedProduct = productRepository.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + p.getId()));
            total += managedProduct.getPrice();
            managedProducts.add(managedProduct);
        }

        // 2. Set the calculated total
        order.setProducts(managedProducts);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    // 5. Update Status
    @Transactional
    public Order updateStatus(String id, OrderStatus newStatus) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setOrderStatus(newStatus);
        return orderRepository.save(existingOrder);
    }

    // 6. Delete
    public void deleteOrder(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }




}
