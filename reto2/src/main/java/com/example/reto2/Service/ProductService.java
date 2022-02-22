package com.example.reto2.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.reto2.Repositories.Entities.ProductEntity;
import com.example.reto2.Repositories.Interfaces.ProductRepository;
import com.example.reto2.Service.Models.ProductDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAll(){
        return productRepository.findAll().stream()
            .map(x -> modelMapper.map(x, ProductDTO.class))
            .collect(Collectors.toList());
    }

    public ProductDTO add(ProductDTO product){
        ProductEntity entityToInsert = modelMapper.map(product, ProductEntity.class);
        ProductEntity result = productRepository.save(entityToInsert);
        return modelMapper.map(result, ProductDTO.class);
    }

    public ProductDTO update(Long id, ProductDTO product){
        ProductEntity entityToUpdate = modelMapper.map(product, ProductEntity.class);
        entityToUpdate.setId(id);
        ProductEntity result = productRepository.save(entityToUpdate);
        return modelMapper.map(result, ProductDTO.class);
    }

    public void delete(Long id){
        Optional<ProductEntity> entityToDelete = productRepository.findById(id);
        if(entityToDelete.isPresent()){
            productRepository.delete(entityToDelete.get());
        }
    }

    public Optional<ProductDTO> findById(Long id){
        Optional<ProductEntity> entity = productRepository.findById(id);
        if(entity.isPresent()){
            return Optional.of(modelMapper.map(entity.get(), ProductDTO.class));
        }else{
            return Optional.empty();
        }
    }
}
