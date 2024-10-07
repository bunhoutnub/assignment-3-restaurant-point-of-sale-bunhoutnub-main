package org.csu.cpsc;

public interface OrderTrackerInterface {
    boolean addOrder(Order newOrder);
    String showCurrentOrder();
    Order getNextOrder();
    boolean skipOrder();
}
