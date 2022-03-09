package com.example.reto2.Web.Config;

import com.example.reto2.Service.OrderProductService;
import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DI {
    @Bean
    ProductService createProductService(){
        return new ProductService();
    }

    @Bean
    OrderService createOrderService(){
        return new OrderService();
    }
    
    @Bean
    OrderProductService createOrderProductService(){
        return new OrderProductService();
    }

    @Bean
    ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
