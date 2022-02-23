package com.example.reto2.Web.API;

import java.util.List;

import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.Models.OrderDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<OrderDTO> getOrders() {
        return orderService.getAll();
    }
}
