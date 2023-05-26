package com.orderbook;

import com.orderbook.entity.Order;
import com.orderbook.service.LimitOrderBookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        LimitOrderBookService limitOrderBookService = new LimitOrderBookService();

        Order order1 = new Order(1,120.00,40,0,"bid");
        Order order2 = new Order(2,20.00,80,1,"bid");
        Order order3 = new Order(3,80.00,100,2,"bid");
        Order order4 = new Order(4,700.00,7,3,"ask");
        Order order5 = new Order(5,15.50,1200,4,"ask");
        Order order6 = new Order(6,890.80,3,5,"ask");
        Order order7 = new Order(7,12.80,800,6,"bid");
        Order order8 = new Order(8,1.50,1800,7,"bid");
        Order order9 = new Order(9,1200.00,50,8,"bid");
        Order order10 = new Order(10,16.89,20000,9,"ask");
        Order order11 = new Order(11,15.50,100000,10,"ask");
        Order order12 = new Order(12,12000.00,25,11,"ask");
        Order order13 = new Order(13,100.00,90,12,"bid");
        Order order14 = new Order(14,1.80,400,13,"bid");
        Order order15 = new Order(15,1.00,40000,14,"ask");
        Order order16 = new Order(16,1720.00,4000,15,"ask");

        //add all order books
        limitOrderBookService.addOrder(order1);
        limitOrderBookService.addOrder(order2);
        limitOrderBookService.addOrder(order3);
        limitOrderBookService.addOrder(order4);
        limitOrderBookService.addOrder(order5);
        limitOrderBookService.addOrder(order6);
        limitOrderBookService.addOrder(order7);
        limitOrderBookService.addOrder(order8);
        limitOrderBookService.addOrder(order9);
        limitOrderBookService.addOrder(order10);
        limitOrderBookService.addOrder(order11);
        limitOrderBookService.addOrder(order12);
        limitOrderBookService.addOrder(order13);
        limitOrderBookService.addOrder(order14);
        limitOrderBookService.addOrder(order15);
        limitOrderBookService.addOrder(order16);


        //print all currently added orders
        List<Order> ordersList = limitOrderBookService.getAllOrders();
        System.out.println("\n======================================================unOrdered List of Orders========================================\n");
        for (Order order: ordersList){
            System.out.println(order);
        }

        //print all orders ordered in their priority
        List<Order> priorityOrdered = limitOrderBookService.getAllOrdersOrderedByPriority();
        System.out.println("\n======================================================Priority Ordered List of Orders========================================\n");
        for (Order order: priorityOrdered){
            System.out.println(order);
        }

        System.out.println("\n======================================================Priority Ordered List of Orders after updating an order ========================================\n");
        limitOrderBookService.updateOrder(2,22.50,150);
        List<Order> priorityupdatedList = limitOrderBookService.getAllOrdersOrderedByPriority();
        for (Order order: priorityupdatedList){
            System.out.println(order);
        }

        System.out.println("\n======================================================Priority Ordered List of Orders after removing order========================================\n");

        limitOrderBookService.deleteOrder(16);
        List<Order> listAfterDelete = limitOrderBookService.getAllOrdersOrderedByPriority();
        for (Order order: listAfterDelete){
            System.out.println(order);
        }

        System.out.println("\n======================================================get all orders at a given price level and side========================================\n");

        List<Order> priceLevel = limitOrderBookService.getOrdersAtPriceLevel(16,"bid",50);
        for (Order order: priceLevel){
            System.out.println(order);
        }
    }
}