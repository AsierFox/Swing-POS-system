package com.devdream.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This bill shows the result of the proceed payment.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class BillView extends JFrame {

	private static final long serialVersionUID = -8859657268655367380L;

	public BillView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		JLabel lblBillInfo = new JLabel("Bill Info");
		lblBillInfo.setBounds(170, 11, 46, 14);
		getContentPane().add(lblBillInfo);
		
		
		
		renderer.render();
	}
}
