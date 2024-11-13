/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receiptcalculator;

/**
 *
 * @author Sonu Sharma
 */
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptManager {

    public static void displayReceipt(Receipt receipt) {
        System.out.println("Receipt:");
        for (Item item : receipt.getItems()) {
            System.out.printf("%-20s %5.2f x %d = %5.2f\n", item.getName(), item.getPrice(), item.getQuantity(), item.getTotalCost());
        }
        System.out.printf("Subtotal: %.2f\n", receipt.calculateSubtotal());
        System.out.printf("Discount: %.2f\n", receipt.calculateDiscount());
        System.out.printf("Tax: %.2f\n", receipt.calculateTax());
        System.out.printf("Total: %.2f\n", receipt.calculateTotal());
    }

    public static void saveReceiptAsText(Receipt receipt, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Receipt:\n");
            for (Item item : receipt.getItems()) {
                writer.write(String.format("%-20s %5.2f x %d = %5.2f\n", item.getName(), item.getPrice(), item.getQuantity(), item.getTotalCost()));
            }
            writer.write(String.format("Subtotal: %.2f\n", receipt.calculateSubtotal()));
            writer.write(String.format("Discount: %.2f\n", receipt.calculateDiscount()));
            writer.write(String.format("Tax: %.2f\n", receipt.calculateTax()));
            writer.write(String.format("Total: %.2f\n", receipt.calculateTotal()));
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
