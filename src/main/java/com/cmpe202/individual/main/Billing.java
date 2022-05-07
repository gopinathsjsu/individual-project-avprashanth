package com.cmpe202.individual.main;

import com.cmpe202.individual.builder.CardBuilder;
import com.cmpe202.individual.builder.InventoryBuilder;
import com.cmpe202.individual.builder.OrderBuilder;
import com.cmpe202.individual.response.ValidateOrder;
import java.io.IOException;
import java.util.Scanner;

public class Billing {

    private static String CARD_DETAILS = "cards.csv";
    private static String INVENTORY = "Inventory.csv";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file name which contains order details");
        String orderData = scanner.nextLine();
        InventoryBuilder.buildInventory(INVENTORY);
        CardBuilder.buildCardData(CARD_DETAILS);
        OrderBuilder.buildOrder(orderData);
        ValidateOrder validateOrder = new ValidateOrder();
        validateOrder.validateStock();

    }

}
