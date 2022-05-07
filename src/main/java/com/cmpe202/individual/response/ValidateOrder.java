package com.cmpe202.individual.response;

import com.cmpe202.individual.builder.InventoryBuilder;
import com.cmpe202.individual.builder.OrderBuilder;
import com.cmpe202.individual.model.InventoryItem;
import com.cmpe202.individual.model.Order;
import com.cmpe202.individual.model.OrderItem;
import com.cmpe202.individual.response.validate.CategoryLimit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ValidateOrder implements  BillingHandler{

    private BillingHandler next;

    public static boolean orderValidated = false;

    public void setBillingHandler(BillingHandler nextBillingHandler) {
        this.next = nextBillingHandler;
    }

    private static String ERROR_FILE = "Error.txt";

    public void validateStock() throws IOException {

        HashMap<String, InventoryItem> inventoryStock = InventoryBuilder.itemMap;
        List<Order> inputData = OrderBuilder.orderList;
        boolean flag = false;

        CategoryLimit categoryLimit = CategoryLimit.getInstance();
        HashMap<String, Integer> categoryLimitMap = categoryLimit.categoryLimitMap;
        FileWriter writeError = new FileWriter(ERROR_FILE);

        for(Order order : inputData) {
            OrderItem item = order.getItem();
            String itemName = item.getItemName();
            int orderQuantity = item.getQuantity();
            InventoryItem inventory = inventoryStock.get(itemName);
            int inventoryQuantity = inventory.getQuantity();
            String category = inventory.getCategory();

            int allowedLimit = categoryLimitMap.get(category);
            boolean withInInventory = inventoryQuantity - orderQuantity >= 0;
            boolean withInAllowed = allowedLimit - orderQuantity >= 0;
            if(withInInventory && withInAllowed) {
                inventory.setQuantity(inventoryQuantity - orderQuantity);
                categoryLimitMap.put(category, allowedLimit - orderQuantity);
            } else {
                writeError.write("Cannot proceed to payment because "+itemName +  (!withInAllowed ? " has exceeded the "
                        + category + " limit. Please resubmit the order with quantity no greater than "+allowedLimit : " of quantity "+orderQuantity +
                        " does not contain in the inventory. Resubmit the order with quantity no greater than "+inventoryQuantity+" for successful " +
                        "transaction") );
                flag = true;
                break;
            }
        }

        if (flag) {
            try {
                writeError.close();
                System.out.println(
                        "Cannot proceed to the payment. Please check Error.txt for more details");
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
