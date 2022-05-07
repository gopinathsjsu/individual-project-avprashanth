package com.cmpe202.individual.model;

public class ItemFactory {

    public static ItemFactory createItem(String itemType) {
        if(itemType.equalsIgnoreCase("Inventory")) {
            return new InventoryItem();
        } else {
            return new OrderItem();
        }
    }

}
