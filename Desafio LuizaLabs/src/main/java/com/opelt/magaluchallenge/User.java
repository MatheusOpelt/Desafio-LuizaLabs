package com.opelt.magaluchallenge;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long userId;
    private String name;
    private List<Order> orders  = new ArrayList<>();

    public User(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
