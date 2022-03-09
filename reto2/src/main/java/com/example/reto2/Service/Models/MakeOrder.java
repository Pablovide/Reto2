package com.example.reto2.Service.Models;

import java.util.ArrayList;

public class MakeOrder {
    private String customerName;
    private ArrayList<ProductDTO> products;
    public MakeOrder() {
    }
    public MakeOrder(String customerName, ArrayList<ProductDTO> products) {
        this.customerName = customerName;
        this.products = products;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public ArrayList<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<ProductDTO> products) {
        this.products = products;
    }

}
