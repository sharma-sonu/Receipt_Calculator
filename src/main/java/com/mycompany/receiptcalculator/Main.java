/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receiptcalculator;

/**
 *
 * @author Sonu Sharma
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Receipt receipt = new Receipt(10, 5); // Discount 10%, Tax 5%

        boolean addingItems = true;
        while (addingItems) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                addingItems = false;
                continue;
            }

            System.out.print("Enter price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            Item item = new Item(name, price, quantity);
            receipt.addItem(item);
        }

        ReceiptManager.displayReceipt(receipt);

        System.out.print("Do you want to save the receipt as a text file? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            ReceiptManager.saveReceiptAsText(receipt, "receipt.txt");
            System.out.println("Receipt saved as receipt.txt");
        }
        
        // PDF saving using PDFBox would go here
    }
}

