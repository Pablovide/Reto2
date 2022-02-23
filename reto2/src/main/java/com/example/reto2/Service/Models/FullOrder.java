package com.example.reto2.Service.Models;

import java.util.ArrayList;

public class FullOrder {
    private OrderDTO order;
    private ArrayList<ProductDTO> products;
    public FullOrder() {
    }
    public FullOrder(OrderDTO order, ArrayList<ProductDTO> products) {
        this.order = order;
        this.products = products;
    }
    public OrderDTO getOrder() {
        return order;
    }
    public void setOrder(OrderDTO order) {
        this.order = order;
    }
    public ArrayList<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<ProductDTO> products) {
        this.products = products;
    }
}
