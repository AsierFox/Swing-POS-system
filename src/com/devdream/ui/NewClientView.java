package com.devdream.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.SubscribeClientController;
import com.devdream.exception.CashNotValidException;
import com.devdream.exception.TextNotValidException;
import com.devdream.ui.custom.ErrorAlert;
import com.devdream.ui.custom.InformationAlert;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 * This view shows the components for subscribing a new client.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class NewClientView extends JFrame {

	private static final long serialVersionUID = 6745170097521269127L;
	
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField subscriberCardCashTextField;
	private JCheckBox goldClientCheckBox;
	
	public NewClientView() {
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		getContentPane().setLayout(null);
		
		// New client label
		JLabel newClientLabel = new JLabel("New Client");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		newClientLabel.setBounds(161, 11, 126, 25);
		getContentPane().add(newClientLabel);
		//
		
		// Client ID
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setBounds(28, 48, 46, 14);
		getContentPane().add(forIdLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(53, 45, 86, 20);
		getContentPane().add(idTextField);
		idTextField.setColumns(10);
		//
		
		// Client Name
		JLabel forNameLabel = new JLabel("Name");
		forNameLabel.setBounds(193, 48, 46, 14);
		getContentPane().add(forNameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(249, 45, 86, 20);
		getContentPane().add(nameTextField);
		//
		
		// Client card
		JLabel forSubscriberCardCashLabel = new JLabel("Subscriber card initial cash");
		forSubscriberCardCashLabel.setBounds(53, 94, 178, 14);
		getContentPane().add(forSubscriberCardCashLabel);
		
		subscriberCardCashTextField = new JTextField();
		subscriberCardCashTextField.setColumns(10);
		subscriberCardCashTextField.setBounds(86, 119, 86, 20);
		getContentPane().add(subscriberCardCashTextField);
		//
		
		// Gold client
		goldClientCheckBox = new JCheckBox("Gold Client");
		goldClientCheckBox.setBounds(238, 118, 97, 23);
		getContentPane().add(goldClientCheckBox);
		//
		
		// Subscribe button
		JButton subscribeClientButton = new JButton("Subscribe client");
		subscribeClientButton.addActionListener((e) -> {
			SubscribeClientController clientSubscriber = new SubscribeClientController();
			
			try {
				clientSubscriber.subscribeClient(idTextField.getText(),
						nameTextField.getText(),
						subscriberCardCashTextField.getText(),
						goldClientCheckBox.isSelected());
				
				InformationAlert.show(this, "Client Subscribed successfully!");
				clearData();
			} catch(CashNotValidException | TextNotValidException err) {
				ErrorAlert.show(this, err.getMessage());
			}
		});
		subscribeClientButton.setBounds(139, 173, 148, 23);
		getContentPane().add(subscribeClientButton);
		//
		
		// Close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener((e) -> this.dispose());
		closeButton.setBounds(173, 209, 89, 23);
		getContentPane().add(closeButton);
		
		renderer.render();
	}

	private void clearData() {
		idTextField.setText("");
		nameTextField.setText("");
		subscriberCardCashTextField.setText("");
		goldClientCheckBox.setSelected(false);
	}
	
}
