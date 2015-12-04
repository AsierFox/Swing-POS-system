package com.devdream.ui;

import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.data.bind.Intent;
import com.devdream.model.Client;
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.ui.custom.ErrorAlert;
import com.devdream.ui.custom.MyComboBox;
import com.devdream.ui.custom.ShopOfferTable;
import com.devdream.ui.custom.TextFieldPlaceHolder;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class POSView extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -3842125628121409727L;
	
	private ShopOfferTable offersTable;
	private MyComboBox<Client> clientsComboBox;
	
	public POSView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		// Commercial information
		JLabel testLabel = new JLabel("Session By: ");
		testLabel.setText(testLabel.getText() + Intent.getInstance().getLogedCommercial().getName());
		testLabel.setBounds(0, 0, 138, 21);
		getContentPane().add(testLabel);
		
		JLabel forCommercialPointsLabel = new JLabel("Commercial points");
		forCommercialPointsLabel.setBounds(484, 84, 105, 14);
		getContentPane().add(forCommercialPointsLabel);
		
		JLabel commercialPointsLabel = new JLabel(Integer.toString(Intent.getInstance().getLogedCommercial().getEarnedPoints()));
		commercialPointsLabel.setBounds(484, 109, 46, 14);
		getContentPane().add(commercialPointsLabel);
		//
		
		// Client Label + ComboBox + Gold client Label
		JLabel clientLabel = new JLabel("Select the client");
		clientLabel.setBounds(10, 30, 105, 21);
		getContentPane().add(clientLabel);
		
		JLabel forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setVisible(false);
		forGoldClientAlertLabel.setBounds(285, 30, 89, 21);
		getContentPane().add(forGoldClientAlertLabel);
		
		clientsComboBox = new MyComboBox<Client>(Intent.getInstance().getClients());
		clientsComboBox.addActionListener((e) -> {
			Client selectedClient = Intent.getInstance().getClients().get(clientsComboBox.getSelectedIndex());
			Intent.getInstance().setActualClient(selectedClient);
			forGoldClientAlertLabel.setVisible(selectedClient.isGoldClient());
		});
		clientsComboBox.setBounds(125, 30, 150, 21);
		getContentPane().add(clientsComboBox);
		//
		// New client button
		JButton newClientButton = new JButton("New Client");
		newClientButton.addActionListener((e) -> {
			new NewClientView();
		});
		newClientButton.setBounds(379, 29, 112, 23);
		getContentPane().add(newClientButton);
		//
		
		// Products table
		JLabel productsTableLabel = new JLabel("Products");
		productsTableLabel.setBounds(70, 63, 68, 21);
		getContentPane().add(productsTableLabel);
		
		JScrollPane productsTableScrollPane = new JScrollPane();
		productsTableScrollPane.setBounds(70, 95, 398, 150);
		getContentPane().add(productsTableScrollPane);
		
		offersTable = new ShopOfferTable();
		productsTableScrollPane.setViewportView(offersTable);
		//
		
		// Sale total information
		JLabel forSubTotalLabel = new JLabel("Sub Total");
		forSubTotalLabel.setBounds(478, 168, 57, 14);
		getContentPane().add(forSubTotalLabel);
		
		JLabel subTotalLabel = new JLabel("0");
		subTotalLabel.setBounds(545, 168, 46, 14);
		getContentPane().add(subTotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setBounds(478, 193, 46, 14);
		getContentPane().add(forTaxLabel);
		
		JLabel taxLabel = new JLabel("0");
		taxLabel.setBounds(543, 193, 46, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setBounds(478, 218, 46, 14);
		getContentPane().add(forTotalLabel);
		
		JLabel totalLabel = new JLabel("0");
		totalLabel.setBounds(543, 218, 46, 14);
		getContentPane().add(totalLabel);
		//
		
		// Select Product Service Label and Radio Buttons
		JButton addShopOfferButton = new JButton("Add product");
		
		JRadioButton productRadioButton = new JRadioButton("product");
		productRadioButton.addActionListener((e) ->
			addShopOfferButton.setText("Add product"));
		productRadioButton.setSelected(true);
		productRadioButton.setBounds(29, 263, 77, 23);
		getContentPane().add(productRadioButton);
		
		JRadioButton serviceRadioButton = new JRadioButton("service");
		serviceRadioButton.addActionListener((e) ->
			addShopOfferButton.setText("Add service"));
		serviceRadioButton.setBounds(29, 285, 77, 23);
		getContentPane().add(serviceRadioButton);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(productRadioButton);
		radioButtonGroup.add(serviceRadioButton);
		//
		
		// Shop offers ComboBox
		JLabel forSelectShopOfferLabel = new JLabel("Select: ");
		forSelectShopOfferLabel.setBounds(10, 243, 58, 14);
		getContentPane().add(forSelectShopOfferLabel);
		
		MyComboBox<Product> productsComboBox = new MyComboBox<Product>(Intent.getInstance().getProducts());
		productsComboBox.setBounds(135, 264, 140, 20);
		getContentPane().add(productsComboBox);
		
		MyComboBox<Service> servicesComboBox = new MyComboBox<Service>(Intent.getInstance().getServices());
		servicesComboBox.setBounds(135, 286, 140, 20);
		getContentPane().add(servicesComboBox);
		//
		
		// Quantity
		JLabel quantityLabel = new JLabel("Qty.");
		quantityLabel.setBounds(285, 267, 46, 14);
		getContentPane().add(quantityLabel);

		TextFieldPlaceHolder quantityTextField = new TextFieldPlaceHolder("quantity");
		quantityTextField.setBounds(316, 264, 58, 20);
		getContentPane().add(quantityTextField);
		quantityTextField.setColumns(10);
		//
		
		// Add product
		addShopOfferButton.addActionListener((e) -> {
			try {
				int qty = Integer.parseInt(quantityTextField.getText().trim()); // TODO Control range
				
				if (e.getActionCommand().contains(productRadioButton.getText()))
					offersTable.addOfferToTable(productsComboBox.getItemAt(productsComboBox.getSelectedIndex()), qty);
				else
					offersTable.addOfferToTable(servicesComboBox.getItemAt(servicesComboBox.getSelectedIndex()), qty);
			} catch(NumberFormatException err) {
				ErrorAlert.show(this, "The Quantity must be valid!"); // TODO My exception?
			}
		});
		addShopOfferButton.setBounds(285, 285, 126, 23);
		getContentPane().add(addShopOfferButton);
		//
		
		// Logout and Exit Buttons
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener((e) -> {
			LoginLogoutController logout = new LoginLogoutController(this, LoginView.class.getName());
			logout.logout();
		});
		logoutButton.setBounds(441, 256, 89, 23);
		getContentPane().add(logoutButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(441, 285, 89, 23);
		getContentPane().add(exitButton);
		
		// Show ui
		renderer.render();
	}
	
	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}

}