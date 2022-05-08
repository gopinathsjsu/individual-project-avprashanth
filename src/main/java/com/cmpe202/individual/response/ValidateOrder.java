package com.cmpe202.individual.response;

import com.cmpe202.individual.builder.InventoryBuilder;
import com.cmpe202.individual.builder.OrderBuilder;
import com.cmpe202.individual.model.InventoryItem;
import com.cmpe202.individual.model.Order;
import com.cmpe202.individual.model.OrderItem;
import com.cmpe202.individual.model.factory.Category;
import com.cmpe202.individual.model.factory.CategoryFactory;
import com.cmpe202.individual.response.validate.CategoryLimit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidateOrder implements  BillingHandler{

    private BillingHandler next;
    public void setBillingHandler(BillingHandler nextBillingHandler) {
        this.next = nextBillingHandler;
    }
    public static List<String> errorMessages = new ArrayList<String>();

    public void validateStock() throws IOException {

        HashMap<String, InventoryItem> inventoryStock = InventoryBuilder.itemMap;
        List<Order> inputData = OrderBuilder.orderList;
        boolean errorEncountered = false;
        CategoryLimit categoryLimit = CategoryLimit.getInstance();
        HashMap<String, Integer> categoryLimitMap = categoryLimit.categoryLimitMap;


        for(Order order : inputData) {
            OrderItem item = order.getItem();
            String itemName = item.getItemName();
            int orderQuantity = item.getQuantity();
            InventoryItem inventory = inventoryStock.get(itemName);
            int inventoryQuantity = inventory.getQuantity();
            String categoryStr = inventory.getCategory();

            CategoryFactory categoryFactory = new CategoryFactory();
            Category category = categoryFactory.getCategory(categoryStr);

            int currentCount = category.getCategoryCount();

            int allowedLimit = categoryLimitMap.get(categoryStr);
            boolean withInInventory = inventoryQuantity - orderQuantity >= 0;
            boolean withInAllowed = allowedLimit - (orderQuantity+currentCount) >= 0;
            if(withInInventory && withInAllowed) {
                inventory.setQuantity(inventoryQuantity - orderQuantity);
                category.setCategoryCount(orderQuantity);
            } else {
                errorMessages.add("Cannot proceed to payment because "+ itemName +  (!withInAllowed ? " has exceeded the category("
                                + categoryStr + ") limit. Please resubmit the order with quantity no greater than "+ (allowedLimit - currentCount) : " of quantity "+ orderQuantity +
                                " does not contain in the inventory. Resubmit the order with quantity no greater than "+ inventoryQuantity +" for successful " +
                                "transaction") );
                errorEncountered = true;
            }
        }

        if (errorEncountered) {
            PrintError printError = new PrintError();
            this.setBillingHandler(printError);
            next.validateStock();
            return;
        }

        PrintReceipt receipt = new PrintReceipt();
        this.setBillingHandler(receipt);
        next.validateStock();
    }




}
