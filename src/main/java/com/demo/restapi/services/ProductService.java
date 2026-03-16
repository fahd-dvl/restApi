package com.demo.restapi.services;

import com.demo.restapi.dtos.ProductFullDTO;
import com.demo.restapi.dtos.ProductPreviewDTO;
import com.demo.restapi.entities.Product;
import com.demo.restapi.mappers.ProductMapper;
import com.demo.restapi.repositories.ProductRepository;
import com.demo.restapi.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    // 1. Get By ID
    public ProductFullDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Use our Mapper to return the DTO
        return ProductMapper.toFullDTO(product);
    }

    // 2. Create
    public ProductFullDTO createProduct(Product product) {
        // The repository saves the entity and returns the saved version with the ID
        Product savedProduct = productRepository.save(product);

        // Return the DTO so the frontend gets the final object
        return ProductMapper.toFullDTO(savedProduct);
    }


    // 3. Delete
    public void deleteProduct(String id) {
        // We check if it exists first so we can throw a clean error if it doesn't
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Product with id " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    // 4. Get List (Sorted by createdAt)
    public List<ProductPreviewDTO> getAllProductsSorted() {
        // We use the built-in Sort object from Spring Data
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(ProductMapper::toPreviewDTO)
                .toList();
    }

    // 5. Update
    @Transactional
    public ProductFullDTO updateProduct(String id, Double newPrice, Integer newStock) {
        // First, find the existing product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update only the permitted fields
        existingProduct.setPrice(newPrice);
        existingProduct.setStock(newStock);

        // Notice: We never call existingProduct.setSeller(...).
        // This is how we "forbid" updating the seller.

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toFullDTO(updatedProduct);
    }






}

