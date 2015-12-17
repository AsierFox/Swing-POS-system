package com.devdream.helper;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFHelper {

	private static final String FILE_PATH = System.getProperty("user.dir") + "/test.pdf";
	
	//
	// Attributes
	private PDDocument doc;
	private PDPageContentStream contentStream;
	private PDPage page;

	//
	// Constructors
	public PDFHelper() {
	    doc = new PDDocument();
	    page = new PDPage();
	    doc.addPage(page);
	    try {
			contentStream = new PDPageContentStream(doc, page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
	// Methods
	public void drawTable(float y, float margin, String[][] content)
			throws IOException
	{
		final int rows = content.length, cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;
		final float cellMargin = 5f;

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
	
	public void drawText(String text, float x, float y, boolean bold) throws IOException {
		contentStream.setFont((bold) ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 10);
		this.drawText(text, x, y);
	}
	
	public void genPDF() {
	    try {
			contentStream.close();
			doc.save(FILE_PATH);
		} catch (COSVisitorException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
