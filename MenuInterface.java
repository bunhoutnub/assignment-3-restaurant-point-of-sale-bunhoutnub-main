package org.csu.cpsc;

public interface MenuInterface {
    void loadMenuItems();
    Item getMenuItem(int index);
    int getNumberOfItems();
    String printMenuItems();
}
