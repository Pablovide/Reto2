package com.example.reto2.Repositories.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.reto2.Repositories.Entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    Optional<ProductEntity> findById(Long id);
}
