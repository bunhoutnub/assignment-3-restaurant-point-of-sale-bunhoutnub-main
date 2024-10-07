package org.csu.cpsc;

public class Item {
    private String name;
    private Double price;
    private int calories;

    public Item(String name, Double price, int calories){
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }

    public int getCalories(){
        return calories;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    @Override
    public String toString(){
        return name + "- price: $" + price + " - calories: " + calories;
    }
}
