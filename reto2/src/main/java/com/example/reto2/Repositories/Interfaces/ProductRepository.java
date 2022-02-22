package com.example.reto2.Repositories.Interfaces;

import java.util.List;

import com.example.reto2.Repositories.Entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    //puede que requiera cambios*
    List<ProductEntity> findByProductId(Long id);
}
