package com.demo.restapi.dtos;

import com.demo.restapi.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFullDTO {

    private String id;
    private String customerName;
    private Double totalAmount;
    private OrderStatus orderStatus;
    private AddressDTO shippingAddress;
    private LocalDateTime createdAt;
    private List<ProductPreviewDTO> products;
}
