package com.devdream.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.devdream.controller.SubscribeClientController;
import com.devdream.ui.custom.InformationAlert;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class NewClientView extends JFrame {

	private static final long serialVersionUID = 6745170097521269127L;
	
	public NewClientView() {
		ViewRenderer renderer = new ViewRenderer(this, 420, 320);
		getContentPane().setLayout(null);
		
		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setBounds(28, 48, 46, 14);
		getContentPane().add(forIdLabel);
		
		JTextField idTextField = new JTextField();
		idTextField.setBounds(53, 45, 86, 20);
		getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		JLabel newClientLabel = new JLabel("New Client");
		newClientLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		newClientLabel.setBounds(161, 11, 126, 25);
		getContentPane().add(newClientLabel);
		
		JLabel forNameLabel = new JLabel("Name");
		forNameLabel.setBounds(193, 48, 46, 14);
		getContentPane().add(forNameLabel);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(249, 45, 86, 20);
		getContentPane().add(nameTextField);
		
		JLabel forSubscriberCardCashLabel = new JLabel("Subscriber card initial cash");
		forSubscriberCardCashLabel.setBounds(53, 94, 178, 14);
		getContentPane().add(forSubscriberCardCashLabel);
		
		JTextField subscriberCardCashTextField = new JTextField();
		subscriberCardCashTextField.setColumns(10);
		subscriberCardCashTextField.setBounds(86, 119, 86, 20);
		getContentPane().add(subscriberCardCashTextField);

		JCheckBox goldClientCheckBox = new JCheckBox("Gold Client");
		goldClientCheckBox.setBounds(238, 118, 97, 23);
		getContentPane().add(goldClientCheckBox);
		
		JButton subscribeClientButton = new JButton("Subscribe client");
		subscribeClientButton.addActionListener((e) -> {
			// TODO Control errors
			SubscribeClientController clientSubscriber = new SubscribeClientController();
			
			clientSubscriber.subscribeClient(idTextField.getText(),
					nameTextField.getText(),
					Float.parseFloat(subscriberCardCashTextField.getText()),
					goldClientCheckBox.isSelected());
			
			InformationAlert.show(this, "Client Subscribed!");
		});
		subscribeClientButton.setBounds(139, 173, 117, 23);
		getContentPane().add(subscribeClientButton);
		
		
		renderer.render();
	}
}