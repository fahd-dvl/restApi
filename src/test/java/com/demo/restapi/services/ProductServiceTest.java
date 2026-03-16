package com.demo.restapi.services;

import com.demo.restapi.dtos.ProductFullDTO;
import com.demo.restapi.entities.Product;
import com.demo.restapi.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = Product.builder()
                .id("prod-123")
                .name("Test Laptop")
                .price(999.99)
                .build();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(productRepository.findById("prod-123")).thenReturn(Optional.of(sampleProduct));

        // Act
        ProductFullDTO result = productService.getProductById("prod-123");

        // Assert
        assertNotNull(result);
        assertEquals("Test Laptop", result.getName());
        verify(productRepository, times(1)).findById("prod-123");
    }

    @Test
    void testCreateProduct_Success() {
        // Arrange
        Product inputProduct = Product.builder().name("New Product").build();
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // Act
        ProductFullDTO result = productService.createProduct(inputProduct);

        // Assert
        assertNotNull(result);
        assertEquals("prod-123", result.getId());
        verify(productRepository, times(1)).save(inputProduct);
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        when(productRepository.existsById("prod-123")).thenReturn(true);

        // Act
        productService.deleteProduct("prod-123");

        // Assert
        verify(productRepository, times(1)).deleteById("prod-123");
    }

    @Test
    void testDeleteProduct_NotFound() {
        // Arrange
        when(productRepository.existsById("wrong-id")).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct("wrong-id");
        });
    }
}
