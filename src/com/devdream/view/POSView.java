package com.devdream.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.devdream.data.bind.Intent;

import javax.swing.JLabel;

public class POSView extends JFrame {
	
	private static final long serialVersionUID = -3842125628121409727L;
	
	private JTable table;
	
	public POSView() {
		ViewRenderer renderer = new ViewRenderer(this);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 69, 250, 94);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setBounds(0, 0, 434, 261);
		getContentPane().add(lblProducts);
		
		
		JLabel testLbl = new JLabel("Session By: ");
		testLbl.setText(testLbl.getText() + Intent.getInstance().getCommercial().getName());
		testLbl.setBounds(100, 100, 434, 261);
		getContentPane().add(testLbl);

		renderer.render();
	}
	
}