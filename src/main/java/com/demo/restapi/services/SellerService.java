package com.demo.restapi.services;

import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Seller;
import com.demo.restapi.mappers.SellerMapper;
import com.demo.restapi.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    // 1. Get List
    public List<SellerPreviewDTO> getAllSellers() {
        return sellerRepository.findAll()
                .stream()
                .map(SellerMapper::toPreviewDTO)
                .toList();
    }

    // 2. Get By ID
    public Seller getById(String id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }

    // 3. Create
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    // 4. Update (Email Forbidden)
    @Transactional
    public Seller updateSeller(String id, String newStoreName) {
        Seller existingSeller = getById(id);

        // We ONLY allow store name updates
        existingSeller.setStoreName(newStoreName);

        // existingSeller.setEmail(...) is NOT called here. Rule enforced!
        return sellerRepository.save(existingSeller);
    }

    // 5. Delete
    public void deleteSeller(String id) {
        sellerRepository.deleteById(id);
    }





}
