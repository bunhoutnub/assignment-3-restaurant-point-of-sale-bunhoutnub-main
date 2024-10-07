package org.csu.cpsc;

import java.util.LinkedList;
import java.util.Queue;

public class OrderTracker implements OrderTrackerInterface {
    private Queue<Order> orderQueue;

    public OrderTracker() {
        orderQueue = new LinkedList<>();
    }

    @Override
   public boolean addOrder(Order newOrder) {
    return orderQueue.add(newOrder); 
    }

    @Override
    public String showCurrentOrder() {
        if (orderQueue.isEmpty()) {
            return "No Orders";
        }
        return orderQueue.peek().getOrder();
    }

    @Override
    public Order getNextOrder() {
        return orderQueue.poll();
    }

    @Override
    public boolean skipOrder() {
        if (!orderQueue.isEmpty()) {
            Order skippedOrder = orderQueue.poll();
            orderQueue.add(skippedOrder);
            return true;
        }
        return false;
    }
}
