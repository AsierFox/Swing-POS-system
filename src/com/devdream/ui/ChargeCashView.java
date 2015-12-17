package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.ChargeCashController;
import com.devdream.exception.CashFormatException;
import com.devdream.model.Client;
import com.devdream.ui.custom.ErrorAlert;
import com.devdream.ui.custom.InformationAlert;

/**
 * View for charge cash for a client subscriber card.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ChargeCashView extends JFrame {

	private static final long serialVersionUID = 6048270431635288419L;
	
	//
	// Attributes
	private JTextField amountChargeCashTextField;
	
	//
	// Constructor
	public ChargeCashView(Client client) {
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		getContentPane().setLayout(null);
		
		// New client label
		JLabel newClientLabel = new JLabel("Charge Client cash");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		newClientLabel.setBounds(139, 11, 159, 25);
		getContentPane().add(newClientLabel);
		//
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forNameLabel.setBounds(89, 87, 46, 14);
		getContentPane().add(forNameLabel);
		//
		
		// Client card
		JLabel forAmountChargeCashLabel = new JLabel("Amount to charge");
		forAmountChargeCashLabel.setBounds(89, 115, 113, 14);
		getContentPane().add(forAmountChargeCashLabel);
		
		amountChargeCashTextField = new JTextField();
		amountChargeCashTextField.setColumns(10);
		amountChargeCashTextField.setBounds(212, 112, 86, 20);
		getContentPane().add(amountChargeCashTextField);
		//
		
		// Subscribe button
		JButton chargeClientCashButton = new JButton("Charge money");
		chargeClientCashButton.addActionListener((e) -> {
			ChargeCashController chargeCashController = new ChargeCashController();
			try {
				chargeCashController.chargeMoney(client, amountChargeCashTextField.getText());
				InformationAlert.show(this, "Charged " + amountChargeCashTextField.getText()
						+ " cash for " + client.getName() + ".");
			} catch (CashFormatException err) {
				ErrorAlert.show(this, err.getMessage());
			}
			clearData();
		});
		chargeClientCashButton.setBounds(139, 158, 121, 23);
		getContentPane().add(chargeClientCashButton);
		//
		
		// Close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener((e) -> this.dispose());
		closeButton.setBounds(139, 200, 121, 23);
		getContentPane().add(closeButton);
		
		JLabel clientLabel = new JLabel(client.getName());
		clientLabel.setBounds(210, 87, 88, 14);
		getContentPane().add(clientLabel);
		
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setBounds(89, 62, 46, 14);
		getContentPane().add(forIdLabel);
		
		JLabel idLabel = new JLabel(client.ID);
		idLabel.setBounds(210, 62, 88, 14);
		getContentPane().add(idLabel);
		
		renderer.render();
	}

	private void clearData() {
		amountChargeCashTextField.setText("");
	}
	
}
