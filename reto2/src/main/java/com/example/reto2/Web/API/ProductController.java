package com.example.reto2.Web.API;

import java.util.ArrayList;
import java.util.List;

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
    ProductController(ProductService productService){
        this.productService = productService;
    }
    
    @GetMapping()
    public List<ProductDTO> GetProducts(){
        return productService.getAll();
    }
}
