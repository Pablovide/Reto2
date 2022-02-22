package com.example.reto2.Repositories.Interfaces;

import java.util.List;

import com.example.reto2.Repositories.Entities.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    //puede que requiera cambios*
    List<OrderEntity> findByOrderId(Long id);
}
