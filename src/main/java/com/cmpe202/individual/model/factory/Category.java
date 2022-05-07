package com.cmpe202.individual.model.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Category {

    public static List<String> essentials = new ArrayList<String>(Arrays.asList(
            "Clothes", "Soap", "Milk"
        ));
    public static List<String> luxuries = new ArrayList<String>(Arrays.asList(
            "Perfume", "Chocolates"
    ));
    public static List<String> miscellaneous = new ArrayList<String>(Arrays.asList(
            "Bedsheets", "Footwear"
    ));


    public static Category create(String item)
    {
        if (essentials.contains(item)) {
            return new Essential();
        } else if (luxuries.contains(item)) {
            return new Luxury();
        }
        return new Miscellaneous();
    }

    public abstract boolean exceededLimit(int quantity);

    public abstract String getCategoryName();

}
