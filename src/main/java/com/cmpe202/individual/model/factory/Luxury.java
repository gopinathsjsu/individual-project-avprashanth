package com.cmpe202.individual.model.factory;

public class Luxury extends Category{

    private int limit = 4;
    private String CategoryName = "Luxury";

    @Override
    public boolean exceededLimit(int quantity) {
        return quantity>this.limit;
    }

    @Override
    public String getCategoryName() {
        return this.CategoryName;
    }

}
