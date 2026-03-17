package com.demo.restapi.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false)
    @Size(min=3,max=100)
    private String name;

    @Column(columnDefinition = "TEXT")
    @Size(min=10,max=2000)
    private String description;

    @Column(nullable = false)
    @DecimalMin(value="0.0", inclusive=false)
    private Double price;

    private String currency;

    private Integer stock;

    private String thumbnail;

    @ElementCollection
    private List<String> images;

    @Column(nullable = false)
    private String category;

    @Column(updatable=false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seller_id")
    @JsonBackReference
    private Seller seller;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }







}
