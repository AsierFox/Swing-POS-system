package com.devdream.model;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.devdream.util.PDFBuilder;
import com.devdream.util.PrinterBuilder;

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
	/** Prints the bill. */
	public void print() throws NullPointerException {
		PrinterBuilder printerHelper = new PrinterBuilder();
		printerHelper.print(this);
	}

	/** Generates the PDF. */
	public void generatePDF() throws IOException, COSVisitorException {
	    PDFBuilder pdf = new PDFBuilder();
		pdf.genPDF(this);
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
