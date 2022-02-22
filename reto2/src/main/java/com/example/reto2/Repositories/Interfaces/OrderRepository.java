package com.example.reto2.Repositories.Interfaces;

import com.example.reto2.Repositories.Entities.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    
}
