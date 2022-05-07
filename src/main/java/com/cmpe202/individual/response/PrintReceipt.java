package com.cmpe202.individual.response;

import com.cmpe202.individual.builder.InventoryBuilder;
import com.cmpe202.individual.builder.OrderBuilder;
import com.cmpe202.individual.model.Order;
import com.cmpe202.individual.model.OrderItem;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrintReceipt implements  BillingHandler{

    private BillingHandler next;

    private String CSV_FILE_NAME = "Output.csv";

    public void setBillingHandler(BillingHandler nextBillingHandler) {
        this.next = nextBillingHandler;
    }

    public void validateStock() throws IOException {

        double totalPrice = 0;
        List<Order> orderList = OrderBuilder.orderList;
        List<String[]> dataLines = new ArrayList<>();

        String[] header = { "Item", "Quantity", "Price", "Total Price" };

        String[] dataArr = null;
        dataLines.add(header);
        for (Order order : orderList) {
            OrderItem item = order.getItem();
            dataArr[0] = item.getItemName();
            dataArr[1] = item.getQuantity();
            double price_per_unit = InventoryBuilder.itemMap.get(item.getItemName()).getPrice();
            double quantity = Double.parseDouble(item.getQuantity());
            dataArr[2] = String.valueOf(quantity * price_per_unit);
            dataArr[3] ="";
            dataLines.add(dataArr);
        }
        for (Order order : orderList) {
            OrderItem item = order.getItem();
            int itemQuantity = Integer.parseInt(item.getQuantity());
            double price = itemQuantity * InventoryBuilder.itemMap.get(item.getItemName()).getPrice();
            totalPrice += price;
        }
        dataLines.get(1)[3] = String.valueOf(totalPrice);
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        System.out.println("Receipt has been generated in Output.csv ");
    }

    public String convertToCSV(String[] data) {

        StringBuilder sb = new StringBuilder();
        int index = 1;
        for(String str : data) {
            if(str == null || str.trim().isEmpty())
                break;
            sb.append(str);
            sb.append("                  ");
            sb.replace(index * 18, sb.length(), "");
            index++;
        }
        return sb.toString();
    }


}
