package com.cmpe202.individual.model;

import com.cmpe202.individual.model.factory.Category;

public class Order {

    private OrderItem item;
    private Card card;

    public Order() {
    }

    public Order(OrderItem item, Card card) {
        this.item = item;
        this.card = card;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
