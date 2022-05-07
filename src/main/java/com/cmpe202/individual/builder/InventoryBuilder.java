package com.cmpe202.individual.builder;

import com.cmpe202.individual.model.InventoryItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class InventoryBuilder {

    public static HashMap<String, InventoryItem> itemMap = new HashMap<String, InventoryItem>();

    public static void buildInventory(String file) {
        String itemStr = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((itemStr = br.readLine()) != null) {
                String[] itemArr = itemStr.split(",");
                String itemName = itemArr[0];
                String category = itemArr[1];
                int quantity = Integer.parseInt(itemArr[2]);
                double price = Double.parseDouble(itemArr[3]);
                InventoryItem item = new InventoryItem(itemName, category, quantity,price);
                itemMap.put(itemName, item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
