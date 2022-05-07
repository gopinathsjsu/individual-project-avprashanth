package com.cmpe202.individual.model;

public class InventoryItem {

    private String item;
    private String category;
    private Integer quantity;
    private double price;

    public String getItem() {
        return item;
    }

    public InventoryItem(String item, String category, Integer quantity, double price) {
        this.item = item;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
