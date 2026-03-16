package com.demo.restapi.entities;


import com.demo.restapi.dtos.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AddressDTO {

    private String street;
    private String city;
    private String country;
    private String zipCode;
}
