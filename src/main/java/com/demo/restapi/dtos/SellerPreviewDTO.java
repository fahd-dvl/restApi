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
public class SellerPreviewDTO {
    private String id;
    private String storeName;
    private Double rating;
    private LocalDateTime createdAt;
}
