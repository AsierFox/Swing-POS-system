package com.devdream.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.ChargeCashController;
import com.devdream.exception.CashFormatException;
import com.devdream.model.Client;
import com.devdream.ui.custom.Alert;

/**
 * View for charge cash for a client subscriber card.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ChargeCashView extends View {

	private static final long serialVersionUID = 6048270431635288419L;
	
	//
	// Attributes
	private ChargeCashController chargeCashController;

	private JLabel actualCashLabel;
	private JTextField amountChargeCashTextField;
	private JButton chargeClientCashButton;
	private JButton closeButton;
	
	//
	// Constructor
	public ChargeCashView(Client client) {
		super();
		setSize(420, 320);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().setLayout(null);

		chargeCashController = new ChargeCashController(client);
		
		loadUI();
		
		loadListeners();
		
		getRenderer().render();
	}

	/** Clears the data from the cash JTextField */
	private void clearData() {
		amountChargeCashTextField.setText("");
	}

	@Override
	protected void loadUI() {
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
		
		JLabel idLabel = new JLabel(chargeCashController.getClient().ID);
		idLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		idLabel.setBounds(210, 62, 88, 14);
		getContentPane().add(idLabel);
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forNameLabel.setBounds(89, 87, 46, 14);
		getContentPane().add(forNameLabel);
		
		JLabel clientLabel = new JLabel(chargeCashController.getClient().getName());
		clientLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		clientLabel.setBounds(210, 87, 88, 14);
		getContentPane().add(clientLabel);
		
		// Actual cash
		JLabel forActualCashLabel = new JLabel("Actual cash");
		forActualCashLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		forActualCashLabel.setBounds(89, 112, 79, 14);
		getContentPane().add(forActualCashLabel);
		
		actualCashLabel = new JLabel(chargeCashController.getClient().getFormattedCash());
		actualCashLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		actualCashLabel.setBounds(210, 112, 88, 14);
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
		chargeClientCashButton = new JButton("Charge money");
		chargeClientCashButton.setBounds(81, 200, 121, 23);
		getContentPane().add(chargeClientCashButton);
		
		// Close button
		closeButton = new JButton("Close");
		closeButton.setBounds(218, 200, 121, 23);
		getContentPane().add(closeButton);
	}

	@Override
	protected void loadListeners() {
		chargeClientCashButton.addActionListener((e) -> {
			try {
				chargeCashController.chargeMoney(amountChargeCashTextField.getText());

				actualCashLabel.setText(chargeCashController.getClient().getFormattedCash());
				
				Alert.showInfo(this, "Charged " + amountChargeCashTextField.getText()
						+ " cash for " + chargeCashController.getClient().getName() + ".");
			} catch (CashFormatException err) {
				Alert.showError(this, err.getMessage());
			}
			clearData();
		});
		
		closeButton.addActionListener((e) -> this.dispose());
	}
	
}
