package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.ChargeCashController;
import com.devdream.exception.CashFormatException;
import com.devdream.helper.StringHelper;
import com.devdream.model.Client;
import com.devdream.ui.custom.Alert;
import com.devdream.util.ViewRenderer;

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
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		getContentPane().setLayout(null);
		
		// New client label
		JLabel newClientLabel = new JLabel("Charge Client cash");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		newClientLabel.setBounds(115, 11, 159, 25);
		getContentPane().add(newClientLabel);
		
		// Client ID
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forIdLabel.setBounds(89, 62, 46, 14);
		getContentPane().add(forIdLabel);
		
		JLabel idLabel = new JLabel(client.ID);
		idLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		idLabel.setBounds(210, 62, 88, 14);
		getContentPane().add(idLabel);
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forNameLabel.setBounds(89, 87, 46, 14);
		getContentPane().add(forNameLabel);
		
		JLabel clientLabel = new JLabel(client.getName());
		clientLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		clientLabel.setBounds(210, 87, 88, 14);
		getContentPane().add(clientLabel);
		
		
		// Actual cash
		JLabel forActualCashLabel = new JLabel("Actual cash");
		forActualCashLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forActualCashLabel.setBounds(89, 112, 79, 14);
		getContentPane().add(forActualCashLabel);
		
		JLabel actualCashLabel = new JLabel(StringHelper.formatAmount(client.getCash()));
		actualCashLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		actualCashLabel.setBounds(210, 112, 46, 14);
		getContentPane().add(actualCashLabel);
		
		// Charge cash
		JLabel forAmountChargeCashLabel = new JLabel("Amount to charge");
		forAmountChargeCashLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forAmountChargeCashLabel.setBounds(89, 149, 113, 14);
		getContentPane().add(forAmountChargeCashLabel);
		
		amountChargeCashTextField = new JTextField();
		amountChargeCashTextField.setColumns(10);
		amountChargeCashTextField.setBounds(212, 146, 86, 20);
		getContentPane().add(amountChargeCashTextField);
		
		// Subscribe button
		JButton chargeClientCashButton = new JButton("Charge money");
		chargeClientCashButton.addActionListener((e) -> {
			ChargeCashController chargeCashController = new ChargeCashController();
			try {
				chargeCashController.chargeMoney(client, amountChargeCashTextField.getText());
				Alert.showInfo(this, "Charged " + amountChargeCashTextField.getText()
						+ " cash for " + client.getName() + ".");
			} catch (CashFormatException err) {
				Alert.showError(this, err.getMessage());
			}
			clearData();
		});
		chargeClientCashButton.setBounds(81, 200, 121, 23);
		getContentPane().add(chargeClientCashButton);
		
		// Close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener((e) -> this.dispose());
		closeButton.setBounds(218, 200, 121, 23);
		getContentPane().add(closeButton);
		
		renderer.render();
	}

	private void clearData() {
		amountChargeCashTextField.setText("");
	}
	
}
