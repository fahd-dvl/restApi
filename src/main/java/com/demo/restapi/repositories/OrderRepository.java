package com.demo.restapi.repositories;

import com.demo.restapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    List<Order> findByProducts_Id(String productId);

    List<Order> findByCustomerName(String customername);

}
