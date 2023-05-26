package com.orderbook.service;

import com.orderbook.entity.Order;

import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

  //in this method the order with priority 0  is considered to have the highest priority
    @Override
    public int compare(Order firstOrder, Order seconfOrder) {
        if (firstOrder.getPriority() < seconfOrder.getPriority()) {
            return -1;
        } else if (firstOrder.getPriority() > seconfOrder.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

}
