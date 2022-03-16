package com.opelt.magaluchallenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private long orderId;
    private double total;
    private Date date;
    private List<Product> products = new ArrayList<>();

    public Order(long orderId, double total, Date date) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
