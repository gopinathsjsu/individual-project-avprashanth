package com.cmpe202.individual.builder;

import com.cmpe202.individual.model.Card;
import com.cmpe202.individual.model.Order;
import com.cmpe202.individual.model.OrderItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OrderBuilder {

    public static List<Order> orderList = new ArrayList<Order>();

    public static void buildOrder(String filePath) {
        boolean header = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String orderStr = "";
            while((orderStr = br.readLine()) != null) {
                if(!header) {
                    header = true;
                    continue;
                }
                String orderArr[] = orderStr.split(",");
                String item = orderArr[0];
                String quantity = orderArr[1];
                Card card = null;
                if(orderArr.length > 2) {
                    String cardNumber = orderArr[2];
                    if(!cardNumber.trim().isEmpty() && !CardBuilder.cardNumbers.containsKey(cardNumber)) {
                        card = new Card(cardNumber);
                        CardBuilder.cardNumbers.put(cardNumber, card);
                    }
                    card = new Card(cardNumber);
                }
                OrderItem orderItem = new OrderItem(item, quantity);
                Order order = new Order(orderItem, card);
                orderList.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
