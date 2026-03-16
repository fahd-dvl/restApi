package com.demo.restapi.dtos;

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
public class ProductFullDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private List<String> images;
    private String thumbnail;
    private String category;
    private LocalDateTime createdAt;
    private Integer stock;
    private SellerPreviewDTO seller;
}
