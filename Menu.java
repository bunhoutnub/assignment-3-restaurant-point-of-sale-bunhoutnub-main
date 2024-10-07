package org.csu.cpsc;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu implements MenuInterface {
    private List<Item> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    @Override
    public void loadMenuItems() {
        menuItems.clear();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/csu/cpsc/menu.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] itemData = line.split(",");
                String itemName = itemData[0];
                double itemPrice = Double.parseDouble(itemData[1]);
                int itemCalories = Integer.parseInt(itemData[2]);
                Item item = new Item(itemName, itemPrice, itemCalories);
                menuItems.add(item);
            }
            System.out.println("Menu loaded with items: " + menuItems);
        } catch (IOException e) {
            System.out.println("Error reading menu file: " + e.getMessage());
        }
    }
    
    


    @Override
    public String printMenuItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItems.size(); i++) {
            sb.append(i).append(": ").append(menuItems.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Item getMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            return menuItems.get(index);
        }
        return null;
    }

    @Override
    public int getNumberOfItems() {
        return menuItems.size();
    }
}
