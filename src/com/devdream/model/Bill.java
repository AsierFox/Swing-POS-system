package com.devdream.model;

import java.io.IOException;

import com.devdream.helper.PDFHelper;

/**
 * The Bill is all the information about
 * the sale. The customer that purchased
 * the sale, the commercial who sold,
 * and the date when was realized.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class Bill {

	//
	// Attributes
	private Commercial commercial;
	private Client client;
	private Sale sale;

	//
	// Constructor
	public Bill(Commercial commercial, Client client, Sale sale) {
		this.commercial = commercial;
		this.client = client;
		this.sale = sale;
	}
	
	//
	// Methods
	
	// TODO Generate Printing
	
	public void generatePDF() throws IOException {
		int x = 100;
	    PDFHelper pdf = new PDFHelper();
	    
	    pdf.drawText("HOLAA", 5, 4, true);
		
		// Sale lines table
	    int saleLines = sale.getSaleLines().size() + 1;
	    String[][] content = new String[saleLines][2];
	    content[0][0] = "Offer name";
	    content[0][1] = "Quantity";
    	for (int i = 1; i < saleLines; ++i) {
    		SaleLine currSaleLine = sale.getSaleLines().get(i - 1);
    		content[i][0] = currSaleLine.getProduct().getName();
    		content[i][1] = Integer.toString(currSaleLine.getQuantity());
    	}
		pdf.drawTable(700, x, content);
		
		pdf.genPDF();
	}
	
	//
	// Getters and Setters
	public Commercial getCommercial() {
		return commercial;
	}

	public Client getClient() {
		return client;
	}

	public Sale getSale() {
		return sale;
	}

}
