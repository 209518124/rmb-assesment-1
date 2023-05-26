package com.orderbook.service;

import com.orderbook.entity.Order;

import java.util.*;

public class LimitOrderBookService {

    private PriorityQueue<Order> bidOrders;
    private PriorityQueue<Order> askOrders;




    public LimitOrderBookService() {

        // OrderComparator compares orders based on their price and priority
        Comparator<Order> orderComparator = new OrderComparator();
        bidOrders = new PriorityQueue<>(orderComparator.reversed());
        askOrders = new PriorityQueue<>(orderComparator);
    }



    public void addOrder(Order order) {
        if (order.getSide().equalsIgnoreCase("bid")) {
            bidOrders.add(order);
        } else if (order.getSide().equalsIgnoreCase("ask")) {
            askOrders.add(order);
        }
    }

    public void deleteOrder(int orderId) {

        Order bidOrder = findOrder(orderId, bidOrders);
        if (bidOrder != null) {
            bidOrders.remove(bidOrder);
            return;
        }


        Order askOrder = findOrder(orderId, askOrders);
        if (askOrder != null) {
            askOrders.remove(askOrder);
        }
    }

    public void updateOrder(int orderId, double newPrice, int newQuantity) {


        Order bidOrder = findOrder(orderId, bidOrders);
        if (bidOrder != null) {
            bidOrders.remove(bidOrder);
            bidOrder.setOrderPrice(newPrice);
            bidOrder.setOrderQuantity(newQuantity);

            int priority = getLowestPriorityOrder().getPriority()+1;

            if(isPriorityAssigned(priority)){
                Random random = new Random();
                int randomNumber = random.nextInt(100) + bidOrders.size();
                bidOrder.setPriority(priority+randomNumber);
            }else{
                bidOrder.setPriority(priority);
            }
            bidOrders.add(bidOrder);
            return;
        }


        Order askOrder = findOrder(orderId, askOrders);
        if (askOrder != null) {
            askOrders.remove(askOrder);
            askOrder.setOrderPrice(newPrice);
            askOrder.setOrderQuantity(newQuantity);
            int priority = getLowestPriorityOrder().getPriority()+1;

            if(isPriorityAssigned(priority)){
                Random random = new Random();
                int randomNumber = random.nextInt(100) + askOrders.size();
                askOrder.setPriority(priority+randomNumber);
            }else{
                askOrder.setPriority(priority);
            }

            askOrders.add(askOrder);
        }
    }

    public Order findOrder(int orderId, PriorityQueue<Order> orders) {
        for (Order order : orders) {
            if (order.getOrderId()==(orderId)) {
                return order;
            }
        }
        return null;
    }


    public List<Order> getOrdersAtPriceLevel(double price, String side,double level) {
        List<Order> orderLists = new ArrayList<>();


        for (Order order : bidOrders) {
            if ((order.getOrderPrice() <= price) &&(order.getOrderPrice() <= level) && order.getSide().equalsIgnoreCase(side)) {
                orderLists.add(order);
            }
        }


        for (Order order : askOrders) {
            if ((order.getOrderPrice() <= price)&&(order.getOrderPrice() <= level) && order.getSide().equalsIgnoreCase(side)) {
                orderLists.add(order);
            }
        }

        return orderLists;
    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        allOrders.addAll(bidOrders);
        allOrders.addAll(askOrders);
        return allOrders;
    }

    public List<Order> getAllOrdersOrderedByPriority() {
        List<Order> allOrders = new ArrayList<>();
        allOrders.addAll(bidOrders);
        allOrders.addAll(askOrders);

        Collections.sort(allOrders, new OrderComparator());

        return allOrders;
    }

    public Order getLowestPriorityOrder() {
        if (bidOrders.isEmpty() && askOrders.isEmpty()) {
            return null; // Return null if both buy and sell orders are empty
        }

        Order lowestPriorityOrder = null;

        if (!bidOrders.isEmpty()) {
            lowestPriorityOrder = bidOrders.peek();
        }

        if (!askOrders.isEmpty()) {
            Order askOrder = askOrders.peek();
            if (lowestPriorityOrder == null || askOrder.getPriority() < lowestPriorityOrder.getPriority()) {
                lowestPriorityOrder = askOrder;
            }
        }

        return lowestPriorityOrder;
    }


    public boolean isPriorityAssigned(int priority) {
        for (Order order : bidOrders) {
            if (order.getPriority() == priority) {
                return true;
            }
        }

        for (Order order : askOrders) {
            if (order.getPriority() == priority) {
                return true;
            }
        }

        return false;
    }

    public PriorityQueue<Order> getBidOrders() {
        return bidOrders;
    }

    public void setBidOrders(PriorityQueue<Order> bidOrders) {
        this.bidOrders = bidOrders;
    }

    public PriorityQueue<Order> getAskOrders() {
        return askOrders;
    }

    public void setAskOrders(PriorityQueue<Order> askOrders) {
        this.askOrders = askOrders;
    }
}
