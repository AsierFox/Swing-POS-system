package com.devdream.util;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.devdream.model.Bill;
import com.devdream.model.SaleLine;

public class PDFBuilder {

	private static final String FILE_PATH = System.getProperty("user.dir") + "/test.pdf";
	
	//
	// Attributes
	private PDDocument doc;
	private PDPageContentStream contentStream;
	private PDPage page;

	//
	// Constructors
	public PDFBuilder() throws IOException {
	    doc = new PDDocument();
	    page = new PDPage();
	    doc.addPage(page);
		contentStream = new PDPageContentStream(doc, page);
	}

	//
	// Methods
	public void genPDF(Bill bill) throws IOException, COSVisitorException {
		// Sale lines table
	    String[][] content = new String[bill.getSale().getSaleLines().size()][2];
	    content[0][0] = "Offer name";
	    content[0][1] = "Offer descriptions";
	    content[0][2] = "Quantity";
	    int i = 1;
    	for (SaleLine sl : bill.getSale().getSaleLines()) {
    		content[i][0] = sl.getProduct().getName();
    		content[i][1] = sl.getProduct().getDescription();
    		content[i++][2] = Integer.toString(sl.getQuantity());
    	}
		drawTable(700, 100, content);
		

		close();
	}
	
	private void close() throws IOException, COSVisitorException {
		contentStream.close();
		doc.save(FILE_PATH);
	}

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

		// Draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			nextx += colWidth;
		}

		// Now add the text
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
	}
	
	private void drawText(String text, float x, float y) throws IOException {
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(x, y);
		contentStream.drawString(text);
		contentStream.endText();
	}
	
	private void drawText(String text, float x, float y, boolean bold) throws IOException {
		contentStream.setFont(bold ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 10);
		this.drawText(text, x, y);
	}
	
}
