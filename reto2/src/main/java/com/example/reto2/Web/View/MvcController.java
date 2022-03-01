package com.example.reto2.Web.View;

import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.ProductService;
import com.example.reto2.Service.Models.FullOrder;
import com.example.reto2.Service.Models.OrderDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class MvcController {
    private final OrderService orderService;
    private final ProductService productService;

    public MvcController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("home");
        OrderDTO order = orderService.getCurrentOrder();
        mv.addObject("products", productService.getAll());
        if  (order != null) {
            var fullOrder = orderService.getFullOrderByOrderId(order.getId());
            mv.addObject("fullCurrentOrder", fullOrder);
        } else {
            return new ModelAndView("redirect:/newOrder");
        }
        return mv;
    }
}
