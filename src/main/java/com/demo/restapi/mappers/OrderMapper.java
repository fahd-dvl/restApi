package com.demo.restapi.mappers;

import com.demo.restapi.dtos.OrderFullDTO;
import com.demo.restapi.dtos.ProductPreviewDTO;
import com.demo.restapi.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderFullDTO toFullDTO(Order order) {
        if (order == null) return null;


        List<ProductPreviewDTO> productDTOs = order.getProducts().stream()
                .map(ProductMapper::toPreviewDTO)
                .collect(Collectors.toList());

        return OrderFullDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .products(productDTOs)
                .build();
    }
}
