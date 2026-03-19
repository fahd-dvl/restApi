package com.demo.restapi.services;

import com.demo.restapi.dtos.SellerPreviewDTO;
import com.demo.restapi.entities.Seller;
import com.demo.restapi.exceptions.BusinessLogicException;
import com.demo.restapi.exceptions.ResourceNotFoundException;
import com.demo.restapi.mappers.SellerMapper;
import com.demo.restapi.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    // 1. Get List
    public Page<SellerPreviewDTO> getAllSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable)
                .map(SellerMapper::toPreviewDTO);
    }

    // 2. Get By ID
    public Seller getById(String id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller with id "+id+" was not found"));
    }

    // 3. Create
    public Seller createSeller(Seller seller) {
        if (sellerRepository.existsByEmail(seller.getEmail())) {
            // We throw our custom exception, NOT a generic RuntimeException
            throw new BusinessLogicException("Email '" + seller.getEmail() + "' is already registered.");
        }
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
        if (!sellerRepository.existsById(id)){
            throw new ResourceNotFoundException("Seller with id: "+id+" doesn't exist");
        }
        sellerRepository.deleteById(id);
    }





}
