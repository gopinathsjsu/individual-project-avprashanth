package com.cmpe202.individual.builder;

import com.cmpe202.individual.model.Card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardBuilder {

    public static Map<String, Card> cardNumbers = new HashMap<String, Card>();

    public static void buildCardData(String filePath) {
        String cardNumber = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            while ((cardNumber = br.readLine()) != null)
            {
                Card card = new Card(cardNumber);
                cardNumbers.put(cardNumber, card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
