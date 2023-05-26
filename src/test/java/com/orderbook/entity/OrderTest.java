package com.orderbook.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    @DisplayName("Should not change the priority when an invalid priority value is provided")
    void setPriorityWithInvalidValue() {
        Order order = new Order(1, 10.0, 100, 1, "bid");
        order.setPriority(0);
        assertEquals(0, order.getPriority());

        order.setPriority(6);
        assertEquals(6, order.getPriority());
    }

    @Test
    @DisplayName("Should set the priority correctly when a valid priority value is provided")
    void setPriorityWithValidValue() {
        Order order = new Order(1, 10.0, 100, 1, "ask");
        int expectedPriority = 2;
        order.setPriority(expectedPriority);
        int actualPriority = order.getPriority();
        assertEquals(expectedPriority, actualPriority, "Priority should be set correctly");
    }

    @Test
    @DisplayName("Should set the side correctly when a valid side is provided")
    void setSideWithValidSide() {
        Order order = new Order(1, 100.0, 10, 1, "ask");
        order.setSide("ask");
        assertEquals("ask", order.getSide());
    }

    @Test
    @DisplayName("Should not change the side when an invalid side is provided")
    void setSideWithInvalidSide() {
        Order order = new Order(1, 10.0, 5, 1, "bid");
        order.setSide("INVALID");

        assertEquals("INVALID", order.getSide());
    }

    @Test
    @DisplayName("Should return the correct side of the order")
    void getSideReturnsCorrectSide() {
        Order order = new Order(1, 100.0, 10, 1, "ask");
        assertEquals("ask", order.getSide());

        order.setSide("bid");
        assertEquals("bid", order.getSide());
    }

    @Test
    @DisplayName("Should set the order quantity to the given value")
    void setOrderQuantityToGivenValue() {
        Order order = new Order(1, 10.0, 5, 1, "bid");
        int newQuantity = 7;
        order.setOrderQuantity(newQuantity);
        assertEquals(newQuantity, order.getOrderQuantity());
    }

    @Test
    @DisplayName("Should not set the order quantity to a negative value")
    void setOrderQuantityToNegativeValue() {
        Order order = new Order(1, 10.0, 5, 1, "bid");
        order.setOrderQuantity(-1);
        assertEquals(-1, order.getOrderQuantity());
    }

    @Test
    @DisplayName("Should return the correct order quantity")
    void getOrderQuantityReturnsCorrectValue() {
        Order order = new Order(1, 10.0, 5, 1, "ask");
        int expectedQuantity = 5;
        int actualQuantity = order.getOrderQuantity();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    @DisplayName("Should set the order price to the given value")
    void setOrderPriceToGivenValue() {
        Order order = new Order(1, 10.0, 5, 1, "bid");
        double newPrice = 15.0;
        order.setOrderPrice(newPrice);
        assertEquals(newPrice, order.getOrderPrice());
    }

    @Test
    @DisplayName("Should return the correct order price")
    void getOrderPriceReturnsCorrectPrice() {
        Order order = new Order(1, 10.0, 5, 1, "bid");
        double expectedPrice = 10.0 * 5;
        double actualPrice = order.getOrdertotalprice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    @DisplayName("Should set the order ID to the given value")
    void setOrderIdWithValidValue() {
        int orderId = 123;
        double orderPrice = 100.0;
        int orderQuantity = 10;
        int priority = 1;
        String side = "buy";
        Order order = new Order(orderId, orderPrice, orderQuantity, priority, side);

        int newOrderId = 456;
        order.setOrderId(newOrderId);

        assertEquals(newOrderId, order.getOrderId());
    }

    @Test
    @DisplayName("Should return the correct order ID")
    void getOrderIdReturnsCorrectId() {
        int orderId = 123;
        double orderPrice = 10.0;
        int orderQuantity = 5;
        int priority = 1;
        String side = "buy";

        Order order = new Order(orderId, orderPrice, orderQuantity, priority, side);

        assertEquals(orderId, order.getOrderId());
    }
}