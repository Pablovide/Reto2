package com.example.reto2.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.reto2.Repositories.Entities.OrderProductEntity;
import com.example.reto2.Repositories.Interfaces.OrderProductRepository;
import com.example.reto2.Service.Models.OrderProductDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderProductDTO> getAll() {
        return orderProductRepository.findAll().stream()
                .map(x -> modelMapper.map(x, OrderProductDTO.class))
                .collect(Collectors.toList());
    }

    public OrderProductDTO add(OrderProductDTO orderProduct) {
        OrderProductEntity entityToInsert = modelMapper.map(orderProduct, OrderProductEntity.class);
        OrderProductEntity result = orderProductRepository.save(entityToInsert);
        return modelMapper.map(result, OrderProductDTO.class);
    }

    public OrderProductDTO update(Long id, OrderProductDTO orderProduct) {
        OrderProductEntity entityToUpdate = modelMapper.map(orderProduct, OrderProductEntity.class);
        entityToUpdate.setId(id);
        OrderProductEntity result = orderProductRepository.save(entityToUpdate);
        return modelMapper.map(result, OrderProductDTO.class);
    }

    public void delete(Long id) {
        Optional<OrderProductEntity> entityToDelete = orderProductRepository.findById(id);
        if (entityToDelete.isPresent()) {
            orderProductRepository.delete(entityToDelete.get());
        }
    }

    public OrderProductDTO findById(Long id) {
        Optional<OrderProductEntity> entity = orderProductRepository.findById(id);
        if (entity.isPresent()) {
            return modelMapper.map(entity.get(), OrderProductDTO.class);
        } else {
            return null;
        }
    }

    public ArrayList<OrderProductDTO> findByOrderId(Long id) {
        Optional<ArrayList<OrderProductEntity>> entity = orderProductRepository.findByOrderId(id);
        if (entity.isPresent()) {
            return (ArrayList<OrderProductDTO>) entity.stream().map(x -> modelMapper.map(x, OrderProductDTO.class))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
