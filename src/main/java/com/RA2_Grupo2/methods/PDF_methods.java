package com.RA2_Grupo2.methods;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF_methods {

	public static void createDocument() {
		Document document = new Document();
		Date d = new Date();
		Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		Font productsFont = new Font(Font.FontFamily.HELVETICA, 10);

		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("src/main/resources/pdfs/Inventory_" + d.getTime() + ".pdf"));
			document.open();

			Font f = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
			Paragraph header = new Paragraph("Registered products in the inventory", f);
			header.setAlignment(Element.ALIGN_CENTER);

			document.add(header);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Creating table with 4 columns

			PdfPTable productTable = new PdfPTable(4);

			// Headers

			PdfPCell cell = new PdfPCell(new Phrase("NAME", headerFont));
			productTable.addCell(cell);
			cell = new PdfPCell(new Phrase("CATEGORY", headerFont));
			productTable.addCell(cell);
			cell = new PdfPCell(new Phrase("PRICE", headerFont));
			productTable.addCell(cell);
			cell = new PdfPCell(new Phrase("QUANTITY", headerFont));
			productTable.addCell(cell);

			// Adding products to the table

			for (Product p : SQL_Methods.getProducts()) {
				productTable.addCell(new Phrase(p.getName(), productsFont));
				productTable.addCell(new Phrase(p.getCategory(), productsFont));
				productTable.addCell(new Phrase(String.valueOf(p.getPrice()) + " €", productsFont));
				productTable.addCell(new Phrase(String.valueOf(p.getQuantity()), productsFont));
			}

			document.add(productTable);

			document.newPage();

			header = new Paragraph("Transaction history", f);
			header.setAlignment(Element.ALIGN_CENTER);

			document.add(header);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Creating table with 3 columns

			PdfPTable supplierTable = new PdfPTable(3);

			// Headers

			cell = new PdfPCell(new Phrase("PRODUCT", headerFont));
			supplierTable.addCell(cell);
			cell = new PdfPCell(new Phrase("SUPPLIER", headerFont));
			supplierTable.addCell(cell);
			cell = new PdfPCell(new Phrase("QUANTITY", headerFont));
			supplierTable.addCell(cell);

			// Adding transactions to the table

			for (Transaction t : SQL_Methods.getTransactions()) {
				supplierTable
						.addCell(new Phrase(SQL_Methods.getProductFromId(t.getProductId()).getName(), productsFont));
				supplierTable
						.addCell(new Phrase(SQL_Methods.getSupplierFromId(t.getSupplierId()).getName(), productsFont));
				supplierTable.addCell(new Phrase(String.valueOf(t.getQuantity()), productsFont));
			}

			document.add(supplierTable);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			Paragraph footer = new Paragraph("The moment when this file was created is: " + d);
			footer.setAlignment(Element.ALIGN_CENTER);

			document.add(footer);
			document.close();

			JOptionPane.showMessageDialog(null, "PDF created succesfully.");
		} catch (DocumentException | FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
