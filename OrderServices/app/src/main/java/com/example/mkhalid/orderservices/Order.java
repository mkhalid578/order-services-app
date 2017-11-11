package com.example.mkhalid.orderservices;

/**
 * Created by mkhalid on 11/10/17.
 */

public class Order {

    private String email;

    private String item;

    private int quantity;

    private double cost;

    private String description;

    public Order(String email, String item, int quantity, double cost, String description) {

        this.email = email;
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
    }

    public String getEmail() {
        return this.email;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItem() {
        return item;
    }

}
