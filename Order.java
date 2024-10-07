package org.csu.cpsc;

import java.util.Stack;

public class Order implements OrderInterface {
    private Stack<Item> items;

    public Order() {
        items = new Stack<>();
    }

    @Override
    public boolean addItem(Item item) {
        if (item != null) {
            items.push(item);
            return true;
        }
        return false;
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice(); 
        }
        return total;
    }

    @Override
    public String getOrder() {
        StringBuilder sb = new StringBuilder();
        Stack<Item> tempStack = new Stack<>();
        
        while (!items.isEmpty()) {
            Item item = items.pop();
            tempStack.push(item);
            sb.append(item.toString()).append("\n");
        }

        while (!tempStack.isEmpty()) {
            items.push(tempStack.pop());
        }
        return sb.toString();
    }
}
