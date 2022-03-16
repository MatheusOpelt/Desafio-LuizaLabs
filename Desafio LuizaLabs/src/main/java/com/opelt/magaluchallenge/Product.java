package com.opelt.magaluchallenge;

public class Product {

    private long productId;
    private double value;

    public Product(long productId, double value) {
        this.productId = productId;
        this.value = value;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}


