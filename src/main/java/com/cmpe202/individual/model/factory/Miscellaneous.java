package com.cmpe202.individual.model.factory;

public class Miscellaneous extends Category{

    private int limit = 6;
    private String CategoryName = "Miscellaneous";

    @Override
    public boolean exceededLimit(int quantity) {
        return quantity>this.limit;
    }

    @Override
    public String getCategoryName() {
        return this.CategoryName;
    }
}
