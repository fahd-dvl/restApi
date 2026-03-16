package com.demo.restapi.mappers;

import com.demo.restapi.dtos.ProductFullDTO;
import com.demo.restapi.dtos.ProductPreviewDTO;
import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Product;

import java.util.List;
import java.util.stream.Collectors;


public class ProductMapper {

    public static ProductPreviewDTO toPreviewDTO(Product product){
        if (product ==null){
            return null;
        }
        return ProductPreviewDTO.builder().id(product.getId()).name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .thumbnail(product.getThumbnail())
                .createdAt(product.getCreatedAt())
                .category(product.getCategory()).build();
    }

    public static ProductFullDTO toFullDTO(Product product) {
        if (product == null) return null;




        return ProductFullDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .images(product.getImages())   // Full list of images
                .thumbnail(product.getThumbnail())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .stock(product.getStock())
                .seller(SellerMapper.toPreviewDTO(product.getSeller()))
                .build();
    }
}
