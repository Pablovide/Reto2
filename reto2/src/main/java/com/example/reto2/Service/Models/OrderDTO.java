package com.example.reto2.Service.Models;

import java.util.Date;

import com.example.reto2.Service.Enums.OrderStatus;

public class OrderDTO {
    private long id;
    private Date creationDate;
    private String customerName;
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String costumerName) {
        this.customerName = costumerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
