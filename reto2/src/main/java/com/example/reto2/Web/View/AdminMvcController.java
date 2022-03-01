package com.example.reto2.Web.View;

import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminMvcController {
    private final OrderService orderService;
    private final ProductService productService;

    public AdminMvcController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public ModelAndView orders() {
        ModelAndView mv = new ModelAndView("admin/orders");
        mv.addObject("orders", orderService.getAll());
        return mv;
    }

    @GetMapping("/products")
    public ModelAndView products() {
        ModelAndView mv = new ModelAndView("admin/products");
        mv.addObject("products", productService.getAll());
        return mv;
    }

    @GetMapping("/order/{id}")
    public ModelAndView order(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin/order");
        mv.addObject("fullOrder", orderService.getFullOrderByOrderId(id));
        return mv;
    }

}
