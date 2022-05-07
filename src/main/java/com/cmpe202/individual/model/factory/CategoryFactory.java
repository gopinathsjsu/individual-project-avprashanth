package com.cmpe202.individual.model.factory;

public class CategoryFactory {

    public Category getCategory(String itemType) {
        if(itemType.equalsIgnoreCase("Essentials")) {
            return new Essentials();
        } else if (itemType.equalsIgnoreCase("Luxury")) {
            return new Luxury();
        } else {
            return new Misc();
        }
    }

}
