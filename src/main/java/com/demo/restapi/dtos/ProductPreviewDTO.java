package com.demo.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPreviewDTO {
    private String id;
    private String name;
    private Double price;
    private String currency;
    private String thumbnail;
    private LocalDateTime createdAt;
    private String category;
}
