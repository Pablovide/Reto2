package com.example.reto2.Web.API;

import java.util.ArrayList;

import com.example.reto2.Service.ProductService;
import com.example.reto2.Service.Models.ProductDTO;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("")
    public ArrayList<ProductDTO> getProducts() {
        throw new NotYetImplementedException();
    }
}
