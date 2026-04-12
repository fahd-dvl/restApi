package com.demo.restapi.controllers;

import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Seller;
import com.demo.restapi.services.SellerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
@Validated
public class SellerController {

    private final SellerService sellerService;

    // 1. Get List (Returns Preview DTOs for a lighter response)
    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Page<SellerPreviewDTO>> getAllSellers(@ParameterObject Pageable pageable) {
        Page<SellerPreviewDTO> sellers = sellerService.getAllSellers(pageable);

        // 1. Define the caching policy (e.g., 1 hour)
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.MINUTES)
                .cachePublic()
                .mustRevalidate();

        // 2. Return the response with the Cache-Control header
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(sellers);
    }

    // 2. Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable String id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

    // 3. Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Seller> create(@Valid @RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.createSeller(seller));
    }

    // 4. Update (Restricted to Store Name only)
    @PatchMapping("/{id}")
    public ResponseEntity<Seller> update(@PathVariable String id,
                                         @RequestParam  @NotBlank String storeName) {
        return ResponseEntity.ok(sellerService.updateSeller(id, storeName));
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();

    }






}
