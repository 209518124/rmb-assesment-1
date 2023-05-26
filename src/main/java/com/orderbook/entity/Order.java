package com.orderbook.entity;

public class Order {

    private int orderId;
    private double orderPrice;
    private int orderQuantity;

    private int priority;

    private String side;


    public Order(int orderId, double orderPrice, int orderQuantity, int priority, String side) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.priority = priority;
        this.side = side;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderPrice=" + orderPrice +
                ", orderQuantity=" + orderQuantity +
                ", priority=" + priority +
                ", side='" + side + '\'' +
                '}';
    }
}
