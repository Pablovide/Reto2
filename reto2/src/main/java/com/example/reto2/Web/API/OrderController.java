package com.example.reto2.Web.API;

import java.util.ArrayList;

import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.Models.OrderDTO;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    //private final OrderService orderService;

    @GetMapping("")
    public ArrayList<OrderDTO> getOrders() {
        throw new NotYetImplementedException();
    }
}
