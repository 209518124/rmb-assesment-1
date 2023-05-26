package com.orderbook.service;

import com.orderbook.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LimitOrderBookServiceTest {

    private LimitOrderBookService limitOrderBookService;

    @BeforeEach
    void setUp() {
        limitOrderBookService = new LimitOrderBookService();
    }

    @Test
    @DisplayName("Should delete the order with the given orderId from bidOrders")
    void deleteOrderWhenOrderIdExistsInBidOrders() {
        Order order1 = new Order(1, 100.0, 10, 1, "bid");
        Order order2 = new Order(2, 200.0, 20, 2, "bid");
        Order order3 = new Order(3, 300.0, 30, 3, "bid");

        limitOrderBookService.addOrder(order1);
        limitOrderBookService.addOrder(order2);
        limitOrderBookService.addOrder(order3);

        limitOrderBookService.deleteOrder(2);

        assertNull(limitOrderBookService.findOrder(2, limitOrderBookService.getBidOrders()));
        assertEquals(2, limitOrderBookService.getBidOrders().size());
        assertEquals(order3, limitOrderBookService.getBidOrders().peek());
    }

    @Test
    @DisplayName(
            "Should not delete any order when the given orderId does not exist in both bidOrders and askOrders")
    void deleteOrderWhenOrderIdDoesNotExist() {
        Order order1 = new Order(1, 100.0, 10, 1, "bid");
        Order order2 = new Order(2, 200.0, 20, 2, "ask");
        Order order3 = new Order(3, 300.0, 30, 3, "bid");

        limitOrderBookService.addOrder(order1);
        limitOrderBookService.addOrder(order2);
        limitOrderBookService.addOrder(order3);

        limitOrderBookService.deleteOrder(4);

        assertEquals(3, limitOrderBookService.getAllOrders().size());
        assertTrue(limitOrderBookService.getAllOrders().contains(order1));
        assertTrue(limitOrderBookService.getAllOrders().contains(order2));
        assertTrue(limitOrderBookService.getAllOrders().contains(order3));
    }

    @Test
    @DisplayName("Should delete the order with the given orderId from askOrders")
    void deleteOrderWhenOrderIdExistsInAskOrders() {
        Order order1 = new Order(1, 100.0, 10, 1, "ask");
        Order order2 = new Order(2, 200.0, 20, 2, "ask");
        Order order3 = new Order(3, 300.0, 30, 3, "ask");

        limitOrderBookService.addOrder(order1);
        limitOrderBookService.addOrder(order2);
        limitOrderBookService.addOrder(order3);

        limitOrderBookService.deleteOrder(2);

        assertNull(limitOrderBookService.findOrder(2, limitOrderBookService.getAskOrders()));
        assertEquals(2, limitOrderBookService.getAskOrders().size());
        assertEquals(order1, limitOrderBookService.getAskOrders().peek());
        assertEquals(order3, limitOrderBookService.getAskOrders().toArray()[1]);
    }

    @Test
    @DisplayName("Should delete the ask order when the orderId is found in askOrders")
    void deleteOrderWhenOrderIdIsInAskOrders() {
        Order askOrder = new Order(1, 100.0, 10, 1, "ask");
        limitOrderBookService.addOrder(askOrder);

        limitOrderBookService.deleteOrder(askOrder.getOrderId());

        assertTrue(limitOrderBookService.getAllOrders().isEmpty());
    }

    @Test
    @DisplayName("Should delete the bid order when the orderId is found in bidOrders")
    void deleteOrderWhenOrderIdIsInBidOrders() { // Create a mock order
        Order order = new Order(1, 100.0, 10, 1, "bid");

        // Add the order to the bidOrders queue
        limitOrderBookService.addOrder(order);

        // Delete the order
        limitOrderBookService.deleteOrder(order.getOrderId());

        // Verify that the order has been removed from the bidOrders queue
        assertNull(
                limitOrderBookService.findOrder(
                        order.getOrderId(), limitOrderBookService.getBidOrders()));
    }

    @Test
    @DisplayName(
            "Should not delete any order when the orderId is not found in both bidOrders and askOrders")
    void deleteOrderWhenOrderIdIsNotFound() {
        int orderId = 1;
        limitOrderBookService.addOrder(new Order(2, 100.0, 10, 1, "bid"));
        limitOrderBookService.addOrder(new Order(3, 200.0, 20, 2, "ask"));

        int initialBidOrdersSize = limitOrderBookService.getBidOrders().size();
        int initialAskOrdersSize = limitOrderBookService.getAskOrders().size();

        limitOrderBookService.deleteOrder(orderId);

        int finalBidOrdersSize = limitOrderBookService.getBidOrders().size();
        int finalAskOrdersSize = limitOrderBookService.getAskOrders().size();

        assertEquals(initialBidOrdersSize, finalBidOrdersSize);
        assertEquals(initialAskOrdersSize, finalAskOrdersSize);
    }
}