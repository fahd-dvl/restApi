package com.demo.restapi.controllers;

import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Seller;
import com.demo.restapi.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    // 1. Get List (Returns Preview DTOs for a lighter response)
    @GetMapping
    public List<SellerPreviewDTO> getAll() {
        return sellerService.getAllSellers();
    }

    // 2. Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable String id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

    // 3. Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seller create(@RequestBody Seller seller) {
        return sellerService.createSeller(seller);
    }

    // 4. Update (Restricted to Store Name only)
    @PatchMapping("/{id}")
    public ResponseEntity<Seller> update(@PathVariable String id,
                                         @RequestParam String storeName) {
        return ResponseEntity.ok(sellerService.updateSeller(id, storeName));
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        sellerService.deleteSeller(id);
    }






}
