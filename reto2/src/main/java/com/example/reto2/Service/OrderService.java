package com.example.reto2.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.reto2.Repositories.Entities.OrderEntity;
import com.example.reto2.Repositories.Interfaces.OrderRepository;
import com.example.reto2.Service.Models.OrderDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAll(){
        return orderRepository.findAll().stream()
            .map(x -> modelMapper.map(x, OrderDTO.class))
            .collect(Collectors.toList());
    }

    public OrderDTO add(OrderDTO order){
        OrderEntity entityToInsert = modelMapper.map(order, OrderEntity.class);
        OrderEntity result = orderRepository.save(entityToInsert);
        return modelMapper.map(result, OrderDTO.class);
    }

    public OrderDTO update(Long id, OrderDTO order){
        OrderEntity entityToUpdate = modelMapper.map(order, OrderEntity.class);
        entityToUpdate.setId(id);
        OrderEntity result = orderRepository.save(entityToUpdate);
        return modelMapper.map(result, OrderDTO.class);
    }

    public void delete(Long id){
        Optional<OrderEntity> entityToDelete = orderRepository.findById(id);
        if(entityToDelete.isPresent()){
            orderRepository.delete(entityToDelete.get());
        }
    }

    public OrderDTO findById(Long id){
        Optional<OrderEntity> entity = orderRepository.findById(id);
        if(entity.isPresent()){
            return modelMapper.map(entity.get(), OrderDTO.class);
        }else{
            return null;
        }
    }
}
