package com.demo.restapi.controllers;

import com.demo.restapi.dtos.ProductFullDTO;
import com.demo.restapi.dtos.ProductPreviewDTO;
import com.demo.restapi.entities.Product;
import com.demo.restapi.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductPreviewDTO> getAllSorted() {
        return productService.getAllProductsSorted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFullDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductFullDTO create(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public ProductFullDTO update(@PathVariable String id,
                                 @Positive @RequestParam Double price,
                                 @RequestParam Integer stock) {
        return productService.updateProduct(id, price, stock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category")
    public List<ProductFullDTO> getByCategory(@RequestParam String category){
        return productService.getProductsByCategory(category);
    }
}
