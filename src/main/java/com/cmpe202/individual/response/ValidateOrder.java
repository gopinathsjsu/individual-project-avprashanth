package com.cmpe202.individual.response;

import com.cmpe202.individual.builder.InventoryBuilder;
import com.cmpe202.individual.builder.OrderBuilder;
import com.cmpe202.individual.model.InventoryItem;
import com.cmpe202.individual.model.Order;
import com.cmpe202.individual.model.OrderItem;
import com.cmpe202.individual.response.validate.CategoryLimit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidateOrder implements  BillingHandler{

    private BillingHandler next;

    public static boolean orderValidated = false;

    public void setBillingHandler(BillingHandler nextBillingHandler) {
        this.next = nextBillingHandler;
    }

    public void validateStock() throws IOException {

        HashMap<String, InventoryItem> inventoryStock = InventoryBuilder.itemMap;
        List<Order> inputData = OrderBuilder.orderList;
        System.out.println(inventoryStock);
        boolean flag = false;

        CategoryLimit categoryLimit = CategoryLimit.getInstance();
        HashMap<String, Integer> category = categoryLimit.categoryLimitMap;
        int essentialsLimit = category.get("Essentials");
        int luxuriesLimit = category.get("Luxury");
        int miscLimit = category.get("Misc");
        int totalEssentials = 0;
        int totalLuxuries = 0;
        int totalMisc = 0;
        FileWriter writeError = new FileWriter("error.txt");
        ArrayList<String> luxuryList = new ArrayList<String>();
        ArrayList<String> essentialList = new ArrayList<String>();
        ArrayList<String> miscList = new ArrayList<String>();

        for (Order order : inputData) {
            OrderItem item = order.getItem();
            int quantity = Integer.parseInt(item.getQuantity());
            String itemName = item.getItemName();

            if (inventoryStock.get(itemName).getCategory().equalsIgnoreCase("essentials")
                    && itemName.equalsIgnoreCase("shampoo") || itemName.equalsIgnoreCase("milk")
                    || itemName.equalsIgnoreCase("soap") || itemName.equalsIgnoreCase("clothes")) {

                totalEssentials = totalEssentials + quantity;
                essentialList.add(itemName);
                if (totalEssentials > essentialsLimit) {
                    flag = true;
                    writeError.write("You have ordered " + totalEssentials + " essential category items "
                            + "Please verify your cart for the following items { "+ essentialList + " } "+
                            " and resubmit your order with no more than " + essentialsLimit + " essentials" + "\n");
                }
            }

            if (inventoryStock.get(itemName).getCategory().equalsIgnoreCase("luxury")
                    && itemName.equalsIgnoreCase("perfume") || itemName.equalsIgnoreCase("chocolates")
                    || itemName.equalsIgnoreCase("handbag") || itemName.equalsIgnoreCase("wallet")) {
                totalLuxuries = totalLuxuries + quantity;
                luxuryList.add(itemName);
                if (totalLuxuries > luxuriesLimit) {
                    flag = true;
                    writeError.write("You have ordered " + totalLuxuries + " essential category items "
                            + "Please verify your cart for the following items { "+ luxuryList + " } "+
                            " and resubmit your order with no more than " + luxuriesLimit + " essentials" + "\n");

                }
            }
            if (inventoryStock.get(itemName).getCategory().equalsIgnoreCase("misc")
                    && itemName.equalsIgnoreCase("bedsheet") || itemName.equalsIgnoreCase("footware")
                    || itemName.equalsIgnoreCase("HomeDecorPiece") || itemName.equalsIgnoreCase("pen")
                    || itemName.equalsIgnoreCase("pencil")) {
                totalMisc = totalMisc + quantity;
                miscList.add(itemName);
                if (totalMisc > miscLimit) {
                    flag = true;
                    writeError.write("You have ordered " + totalMisc + " essential category items "
                            + "Please verify your cart for the following items { "+ miscList + " } "+
                            " and resubmit your order with no more than " + miscLimit + " essentials" + "\n");
                }
            }
        }
        if (flag) {
            try {
                writeError.close();
                System.out.println(
                        "Cannot proceed to the payment. Please check error.txt for more details");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        orderValidated = true;
        PrintReceipt receipt = new PrintReceipt();
        this.setBillingHandler(receipt);
        next.validateStock();
    }


}
