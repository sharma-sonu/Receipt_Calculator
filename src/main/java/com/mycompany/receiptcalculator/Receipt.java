/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receiptcalculator;

/**
 *
 * @author Sonu Sharma
 */
import java.util.ArrayList;

public class Receipt {
    private ArrayList<Item> items;
    private double discountRate;
    private double taxRate;

    public Receipt(double discountRate, double taxRate) {
        items = new ArrayList<>();
        this.discountRate = discountRate;
        this.taxRate = taxRate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Item item : items) {
            subtotal += item.getTotalCost();
        }
        return subtotal;
    }

    public double calculateDiscount() {
        return calculateSubtotal() * discountRate / 100;
    }

    public double calculateTax() {
        return (calculateSubtotal() - calculateDiscount()) * taxRate / 100;
    }

    public double calculateTotal() {
        return calculateSubtotal() - calculateDiscount() + calculateTax();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
