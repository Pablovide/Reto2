package com.example.reto2.Service.Models;

import java.util.HashMap;

public class FullOrder {
    private OrderDTO order;
    private Object[][] products;
    public FullOrder() {
    }
    public FullOrder(OrderDTO order, Object[][] products) {
        this.order = order;
        this.products = products;
    }
    public OrderDTO getOrder() {
        return order;
    }
    public void setOrder(OrderDTO order) {
        this.order = order;
    }
    public Object[][] getProducts() {
        return products;
    }
    public void setProducts(Object[][] products) {
        this.products = products;
    }
}
