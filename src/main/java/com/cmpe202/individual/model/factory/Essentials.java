package com.cmpe202.individual.model.factory;

public class Essentials implements  Category{

    private static int categoryCount = 0;

    @Override
    public int getCategoryCount() {
        return categoryCount;
    }

    @Override
    public void setCategoryCount(double input) {
        categoryCount += input;
    }
}
