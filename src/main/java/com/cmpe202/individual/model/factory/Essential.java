package com.cmpe202.individual.model.factory;

public class Essential extends Category{

    private int limit = 3;
    private String CategoryName = "Essentials";

    @Override
    public boolean exceededLimit(int quantity) {
        return quantity>this.limit;
    }

    @Override
    public String getCategoryName() {
        return this.CategoryName;
    }
}
