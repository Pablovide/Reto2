package com.example.reto2.Web.Config;

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
    ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
