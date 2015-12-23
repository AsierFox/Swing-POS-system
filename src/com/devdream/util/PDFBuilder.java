package com.devdream.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.devdream.helper.StringHelper;
import com.devdream.model.Bill;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Sale;
import com.devdream.model.SaleLine;

/**
 * Class for generating the PDF for the bill.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class PDFBuilder {

	public static final String FILE_PATH = System.getProperty("user.dir") + "/bill.pdf";
	
	//
	// Attributes
	private PDDocument doc;
	private PDPageContentStream contentStream;
	private PDPage page;
	private AtomicInteger x;

	//
	// Constructors
	public PDFBuilder() throws IOException {
	    doc = new PDDocument();
	    page = new PDPage();
	    doc.addPage(page);
		contentStream = new PDPageContentStream(doc, page);
		x = new AtomicInteger(750);
	}

	//
	// Methods
	/**
	 * Generates the bill to a PDF.
	 * @param bill The bill
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public void genPDF(Bill bill) throws IOException, COSVisitorException {
		Commercial commercial = bill.getCommercial();
		Client client = bill.getClient();
		Sale sale = bill.getSale();
	    int y = 65;
	    
		// Title
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 25);
		drawText("2Wheels sale", 200, nextLine());
		setTitleFont();
		drawText("Thanks for buying in our shop!", 180, nextLine());

		// Date
		setNormalFont();
		drawText("Date: " + bill.getSale().getSaleDate(), y, nextLine());
		
		// Commercial
		setTitleFont();
		drawText("Commercial", y, nextLine());
		setNormalFont();
		drawText("ID: " + commercial.ID, y, nextLine());
		drawText("Name: " + commercial.getName(), y, nextLine());
		drawText("Surname: " + commercial.getSurname(), y, nextLine());
		
		// Client
		setTitleFont();
		drawText("Client", y, nextLine());
		setNormalFont();
		drawText("Name: " + client.getName(), y, nextLine());
		drawText("Surname: " + client.getSurname(), y, nextLine());
		if (client.isGoldClient()) {
			drawText("Gold Client", y, nextLine());
		} else {
			if (client.getRemainingCashGoldClient() != 0) {
				drawText("Remaining for the Gold Client: " +
							StringHelper.formatAmount(client.getRemainingCashGoldClient()), y, nextLine());
			} else {
				drawText("You are now a Gold Client, enjoy your future discounts!", y, nextLine());
			}
		}
	    
		// Sale
		setTitleFont();
		drawText("Sale", y, nextLine());
		
		// Product table
		if (!sale.getProductsFromSaleLines().isEmpty()) {
			setTitleFont();
			drawText("Products", y, nextLine());
			setNormalFont();
			generateTable(sale.getProductsFromSaleLines(), "Product", y);
		}

		// Service table
		if (!sale.getServicesFromSaleLines().isEmpty()) {
			setTitleFont();
			drawText("Services", y, nextLine());
			generateTable(sale.getServicesFromSaleLines(), "Service", y);
		}
		
		// Total
		setTitleFont();
		drawText("Sale Total", y, nextLine());
		setNormalFont();
		drawText("Subtotal: " + sale.getFormattedSubtotal(), y, nextLine());
		drawText("Tax: " + sale.getFormattedTax(), y, nextLine());
		if (sale.getDiscountPercentage() > 0) {
			drawText("Discount of: " + sale.getDiscountPercentage() + "%", y, nextLine());
		}
		drawText("Total: " + sale.getFormattedTotal(), y, nextLine());;
		
		close();
	}
	
	/**
	 * Closes the PDF generator.
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	private void close() throws IOException, COSVisitorException {
		contentStream.close();
		doc.save(FILE_PATH);
	}

	/**
	 * Generates the table for specific type of product.
	 * @param saleLines The sale lines of the product
	 * @param title
	 * @param y
	 * @throws IOException
	 */
	private void generateTable(ArrayList<SaleLine> saleLines, String title, int y) throws IOException {
		int i = 1;
	    String[][] content = new String[saleLines.size() + 1][3];
	    content[0][0] = title + " name";
	    content[0][1] = "Unit price";
	    content[0][2] = "Quantity";
    	for (SaleLine sl : saleLines) {
    		content[i][0] = sl.getOffer().getName();
    		content[i][1] = sl.getOffer().getFormattedPrice();
    		content[i++][2] = Integer.toString(sl.getQuantity());
    	}
		drawTable(nextLine(), y, content);
	}
	
	/**
	 * Draws a table on the PDF.
	 * @param y
	 * @param margin
	 * @param content
	 * @throws IOException
	 */
	private void drawTable(float y, float margin, String[][] content) throws IOException {
		final int rows = content.length, cols = content[0].length;
		final float rowHeight = 20f, cellMargin = 5f;
		final float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;

		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			nexty -= rowHeight;
		}

		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			nextx += colWidth;
		}

		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
		
		float textx = margin + cellMargin;
		float texty = y - 15;
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				drawText(text, textx, texty);
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;
			contentStream.setFont(PDType1Font.HELVETICA, 10);
		}
		x.set((int) texty - 15);
	}
	
	/**
	 * Draws a text in the specific position.
	 * @param text The text to draw
	 * @param x The x chord
	 * @param y The y chord
	 * @throws IOException
	 */
	private void drawText(String text, float x, float y) throws IOException {
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(x, y);
		contentStream.drawString(text);
		contentStream.endText();
	}
	
	/** Sets the font to be a title style. */
	private void setTitleFont() throws IOException {
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	}

	/** Sets the font with a normal style. */
	private void setNormalFont() throws IOException {
		contentStream.setFont(PDType1Font.HELVETICA, 10);
	}
	
	/** Goes to the next line to draw. */
	private int nextLine() {
		return x.getAndSet(x.get() - 30);
	}
	
}
