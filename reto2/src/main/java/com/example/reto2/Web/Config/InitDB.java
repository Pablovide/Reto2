package com.example.reto2.Web.Config;

import java.util.Calendar;

import com.example.reto2.Repositories.Entities.OrderEntity;
import com.example.reto2.Repositories.Entities.OrderProductEntity;
import com.example.reto2.Repositories.Entities.ProductEntity;
import com.example.reto2.Repositories.Interfaces.OrderProductRepository;
import com.example.reto2.Repositories.Interfaces.OrderRepository;
import com.example.reto2.Repositories.Interfaces.ProductRepository;
import com.example.reto2.Service.Enums.OrderStatus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepos, ProductRepository productRepos, OrderProductRepository orderProductRepos){
        return args -> {
            // orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Matthus Manntschke", OrderStatus.CANCELLED.toString()));
            // orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Avis Lovewell", OrderStatus.DELIVERED.toString()));
            // orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Marthe Jerson", OrderStatus.CANCELLED.toString()));
            // orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Xerxes Lapham", OrderStatus.SHIPPED.toString()));
            // orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Brandice Loughhead", OrderStatus.ACCEPTED.toString()));
            orderRepos.save(new OrderEntity(Calendar.getInstance().getTime(),"Brandice Loughhead", OrderStatus.PENDING.toString()));
            productRepos.save(new ProductEntity("TV Set", 300, "https://cdn.pixabay.com/photo/2014/04/03/10/32/tv-310801_960_720.png"));
            productRepos.save(new ProductEntity("Game Console", 200, "https://cdn.pixabay.com/photo/2020/05/15/08/28/switch-5172817_960_720.png"));
            productRepos.save(new ProductEntity("Sofa", 100, "https://cdn.pixabay.com/photo/2013/07/12/13/58/settee-147701_960_720.png"));
            productRepos.save(new ProductEntity("Icecream", 5, "https://cdn.pixabay.com/photo/2020/01/01/10/05/sweet-4733259_960_720.png"));
            productRepos.save(new ProductEntity("Beer", 3, "https://cdn.pixabay.com/photo/2017/01/21/21/15/beer-1998293_960_720.jpg"));
            productRepos.save(new ProductEntity("Phone", 500, "https://cdn.pixabay.com/photo/2014/08/05/10/27/iphone-410311_960_720.jpg"));
            // orderProductRepos.save(new OrderProductEntity(1L, 3L, 8));
            // orderProductRepos.save(new OrderProductEntity(2L, 6L, 1));
            // orderProductRepos.save(new OrderProductEntity(3L, 7L, 10));
            // orderProductRepos.save(new OrderProductEntity(4L, 11L, 3));
            // orderProductRepos.save(new OrderProductEntity(5L, 10L, 20));
        };
    }
}
