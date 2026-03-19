package com.demo.restapi.repositories;

import com.demo.restapi.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,String> {
    boolean existsByEmail(String email);
    boolean existsById(String id);
}
