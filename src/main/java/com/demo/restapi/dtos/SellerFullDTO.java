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
public class SellerFullDTO {
    private String id;
    private String storeName;
    private String email;
    private String phone;
    private Double rating;
    private AddressDTO address;
    private LocalDateTime createdAt;
    private List<ProductPreviewDTO> products;
}
