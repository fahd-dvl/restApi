package com.demo.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateDTO {

    private String customerName;
    private AddressDTO shippingAddress;
    private List<String> productIds;
}
