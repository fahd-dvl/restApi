package com.demo.restapi.mappers;

import com.demo.restapi.dtos.ProductFullDTO;
import com.demo.restapi.dtos.ProductPreviewDTO;
import com.demo.restapi.dtos.SellerFullDTO;
import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Product;
import com.demo.restapi.entities.Seller;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class SellerMapper {

    public static SellerPreviewDTO toPreviewDTO(Seller seller) {
        if (seller == null) return null;
        return SellerPreviewDTO.builder()
                .id(seller.getId())
                .storeName(seller.getStoreName())
                .rating(seller.getRating())
                .createdAt(seller.getCreatedAt())
                .build();
    }

    public static SellerFullDTO toFullDTO(Seller seller) {
        if (seller == null) {
            return null;
        }

        // 1. Correctly transforming the List of entities into a List of DTOs
        List<ProductPreviewDTO> productPreviews = null;
        if (seller.getProducts() != null) {
            productPreviews = seller.getProducts().stream()
                    .map(ProductMapper::toPreviewDTO)
                    .collect(Collectors.toList());
        }

        // 2. Build the DTO
        return SellerFullDTO.builder()
                .id(seller.getId())
                .storeName(seller.getStoreName())
                .email(seller.getEmail())
                .phone(seller.getPhone())
                .rating(seller.getRating())
                .address(seller.getAddress())
                .createdAt(seller.getCreatedAt())
                .products(productPreviews) // Pass the list directly - no (casting) needed!
                .build();
    }




}
