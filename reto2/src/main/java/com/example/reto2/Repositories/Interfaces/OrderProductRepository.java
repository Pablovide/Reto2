package com.example.reto2.Repositories.Interfaces;

import java.util.ArrayList;
import java.util.Optional;

import com.example.reto2.Repositories.Entities.OrderProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long>{
    Optional<OrderProductEntity> findById(Long id);

    Optional<ArrayList<OrderProductEntity>> findByOrderId(Long id);
}
