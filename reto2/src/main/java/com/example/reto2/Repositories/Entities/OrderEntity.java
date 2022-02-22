package com.example.reto2.Repositories.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Order")
@Entity(name = "Order")
public class OrderEntity {
    private @Id @GeneratedValue long id;
    private Date creationDate;
    private String customerName;
    private String status;

    public OrderEntity(){}

    public OrderEntity(Date creationDate, String customerName, String status) {
        this.creationDate = creationDate;
        this.customerName = customerName;
        this.status = status;
    }

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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
