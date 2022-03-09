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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<OrderDTO>> getOrders() {
        try {
            var result = orderService.getAll();
            return ResponseEntity.ok(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<FullOrder> getFullOrderDataByOrderId(@PathVariable Long id) throws Exception {
        try {
            var result = orderService.getFullOrderByOrderId(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody MakeOrder makeOrder) {
        try {
            var result = orderService.createOrder(makeOrder);
            return ResponseEntity.ok(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/add/{id}")
    public ResponseEntity addProductToOrder(@PathVariable Long id, @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
                try {
                    orderService.addProductToOrder(id, productId, quantity);
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    return ResponseEntity.badRequest().build();
                }
        

    }

    @PutMapping("/remove/{id}")
    public ResponseEntity removeProductFromOrder(@PathVariable Long id, @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
                try {
                    orderService.removeProductFromOrder(id, productId, quantity);
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    return ResponseEntity.badRequest().build();
                }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteFullOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/accept/{id}")
    public ResponseEntity acceptOrder(@PathVariable Long id) {
        try {
            orderService.acceptOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
