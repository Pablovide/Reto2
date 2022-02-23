package com.example.reto2.Repositories.Interfaces;

import java.util.Optional;

import com.example.reto2.Repositories.Entities.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    Optional<OrderEntity> findById(Long id);
}
