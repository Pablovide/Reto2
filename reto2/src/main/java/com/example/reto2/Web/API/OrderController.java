package com.example.reto2.Web.API;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.reto2.Service.OrderProductService;
import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.ProductService;
import com.example.reto2.Service.Enums.OrderStatus;
import com.example.reto2.Service.Models.FullOrder;
import com.example.reto2.Service.Models.MakeOrder;
import com.example.reto2.Service.Models.OrderDTO;
import com.example.reto2.Service.Models.OrderProductDTO;
import com.example.reto2.Service.Models.ProductDTO;

import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final OrderProductService orderProductService;

    public OrderController(OrderService orderService, ProductService productService,
            OrderProductService orderProductService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    @GetMapping("")
    public List<OrderDTO> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/full/{id}")
    public FullOrder getFullOrderDataByOrderId(@PathVariable Long id) throws Exception {
        try {
            return orderService.getFullOrderByOrderId(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException(id, "Order");
        }
    }

    @PostMapping("")
    public OrderDTO createOrder(@RequestBody MakeOrder makeOrder) {
        return orderService.createOrder(makeOrder);
    }

    @PutMapping("/add/{id}")
    public void addProductToOrder(@PathVariable Long id, @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
        orderService.addProductToOrder(id, productId, quantity);

    }

    @PutMapping("/remove/{id}")
    public void removeProductFromOrder(@PathVariable Long id, @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
        orderService.removeProductFromOrder(id, productId, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteFullOrder(id);
    }
}
