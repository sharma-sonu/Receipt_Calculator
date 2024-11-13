/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receiptcalculator;

/**
 *
 * @author Sonu Sharma
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFGenerator {

    public static void saveReceiptAsPDF(Receipt receipt, String filename) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Set an initial Y position
        float yPosition = 700;
        float lineHeight = 14.5f;

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(lineHeight);
            contentStream.newLineAtOffset(25, yPosition);

            // Title
            contentStream.showText("Receipt:");
            contentStream.newLine();
            yPosition -= lineHeight;

            // Items
            for (Item item : receipt.getItems()) {
                contentStream.showText(String.format("%-20s %5.2f x %d = %5.2f", item.getName(), item.getPrice(), item.getQuantity(), item.getTotalCost()));
                contentStream.newLine();
                yPosition -= lineHeight;

                // Check if yPosition goes off the page, if so add a new page
                if (yPosition <= 50) {
                    contentStream.endText();
                    contentStream.close();

                    // Add a new page
                    PDPage newPage = new PDPage();
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.setLeading(lineHeight);
                    contentStream.newLineAtOffset(25, 700); // Reset yPosition for the new page
                    yPosition = 700;
                }
            }

            // Subtotal, Discount, Tax, Total
            contentStream.showText(String.format("Subtotal: %.2f", receipt.calculateSubtotal()));
            contentStream.newLine();
            contentStream.showText(String.format("Discount: %.2f", receipt.calculateDiscount()));
            contentStream.newLine();
            contentStream.showText(String.format("Tax: %.2f", receipt.calculateTax()));
            contentStream.newLine();
            contentStream.showText(String.format("Total: %.2f", receipt.calculateTotal()));
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }

        // Close and save document
        try {
            document.save(filename);
            document.close();
        } catch (IOException e) {
            System.out.println("Error saving PDF: " + e.getMessage());
        }
    }
}
