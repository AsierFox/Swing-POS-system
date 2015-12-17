package com.devdream.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

public class PrinterHelper {

	private Font font;
	private PrintJob printJob;
	private Graphics page;

	public PrinterHelper() {
		printJob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Print", null);
		font = new Font("Dialog", Font.PLAIN, 10);
	}

	public void print(String text) {
		page = printJob.getGraphics();
		page.setFont(font);
		page.setColor(Color.black);
		page.drawString(text, 60, 60);
		page.dispose();
		printJob.end();
	}

}
