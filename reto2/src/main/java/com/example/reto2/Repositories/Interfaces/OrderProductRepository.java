package com.example.reto2.Repositories.Interfaces;

import java.util.List;

import com.example.reto2.Repositories.Entities.OrderProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long>{
    //puede que requiera cambios*
    List<OrderProductEntity> finByOrderProductId(Long id);
}
