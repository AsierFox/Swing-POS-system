package com.devdream.ui;

import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.controller.SaleController;
import com.devdream.data.bind.Intent;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.model.ShopOffer;
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
	
	private Commercial logedCommercial;
	private Client currentClient;
	private ShopOfferTable offersTable;
	private MyComboBox<Client> clientsComboBox;
	private SaleController saleController;
	
	public POSView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		setLayout(null);
		
		// loged commercial
		logedCommercial = Intent.getInstance().getLogedCommercial();
		
		// Sale Controller
		saleController = new SaleController(this, "TODO"); // TODO add in constructor to change the view to bill view
		
		// Commercial information
		JLabel testLabel = new JLabel("Session By: ");
		testLabel.setText(testLabel.getText() + Intent.getInstance().getLogedCommercial().getName());
		testLabel.setBounds(0, 0, 138, 21);
		add(testLabel);
		
		JLabel forCommercialPointsLabel = new JLabel("Commercial points");
		forCommercialPointsLabel.setBounds(484, 84, 105, 14);
		add(forCommercialPointsLabel);
		
		JLabel commercialPointsLabel = new JLabel(Integer.toString(logedCommercial.getEarnedPoints()));
		commercialPointsLabel.setBounds(484, 109, 46, 14);
		add(commercialPointsLabel);
		//
		
		// Client Label + ComboBox + Gold client Label
		JLabel clientLabel = new JLabel("Select the client:");
		clientLabel.setBounds(10, 30, 105, 21);
		add(clientLabel);
		
		JLabel forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setVisible(false);
		forGoldClientAlertLabel.setBounds(285, 30, 89, 21);
		add(forGoldClientAlertLabel);
		
		clientsComboBox = new MyComboBox<Client>(Intent.getInstance().getClients());
		clientsComboBox.addActionListener((e) -> {
			Client selectedClient = Intent.getInstance().getClients().get(clientsComboBox.getSelectedIndex());
			currentClient = selectedClient;
			Intent.getInstance().setActualClient(selectedClient);
			forGoldClientAlertLabel.setVisible(selectedClient.isGoldClient());
		});
		clientsComboBox.setBounds(125, 30, 150, 21);
		add(clientsComboBox);
		//
		// New client button
		JButton newClientButton = new JButton("New Client");
		newClientButton.addActionListener((e) -> new NewClientView());
		newClientButton.setBounds(379, 29, 112, 23);
		add(newClientButton);
		//
		
		// Products table
		JLabel productsTableLabel = new JLabel("Products");
		productsTableLabel.setBounds(70, 63, 68, 21);
		add(productsTableLabel);
		
		JScrollPane productsTableScrollPane = new JScrollPane();
		productsTableScrollPane.setBounds(70, 95, 398, 150);
		add(productsTableScrollPane);
		
		offersTable = new ShopOfferTable();
		productsTableScrollPane.setViewportView(offersTable);
		//
		
		// Sale total information
		JLabel forSubTotalLabel = new JLabel("Sub Total");
		forSubTotalLabel.setBounds(478, 168, 57, 14);
		add(forSubTotalLabel);
		
		JLabel subtotalLabel = new JLabel("0");
		subtotalLabel.setBounds(545, 168, 46, 14);
		add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setBounds(478, 193, 46, 14);
		add(forTaxLabel);
		
		JLabel taxLabel = new JLabel("0");
		taxLabel.setBounds(543, 193, 46, 14);
		add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setBounds(478, 218, 46, 14);
		add(forTotalLabel);
		
		JLabel totalLabel = new JLabel("0");
		totalLabel.setBounds(543, 218, 77, 14);
		add(totalLabel);
		//
		
		// Select Product Service Label and Radio Buttons
		JButton addShopOfferButton = new JButton("Add product");
		
		JRadioButton productRadioButton = new JRadioButton("product");
		productRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add product"));
		productRadioButton.setSelected(true);
		productRadioButton.setBounds(29, 263, 77, 23);
		add(productRadioButton);
		
		JRadioButton serviceRadioButton = new JRadioButton("service");
		serviceRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add service"));
		serviceRadioButton.setBounds(29, 285, 77, 23);
		add(serviceRadioButton);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(productRadioButton);
		radioButtonGroup.add(serviceRadioButton);
		//
		
		// Shop offers ComboBox
		JLabel forSelectShopOfferLabel = new JLabel("Select: ");
		forSelectShopOfferLabel.setBounds(10, 243, 58, 14);
		add(forSelectShopOfferLabel);
		
		MyComboBox<Product> productsComboBox = new MyComboBox<Product>(Intent.getInstance().getProducts());
		productsComboBox.setBounds(135, 264, 140, 20);
		add(productsComboBox);
		
		MyComboBox<Service> servicesComboBox = new MyComboBox<Service>(Intent.getInstance().getServices());
		servicesComboBox.setBounds(135, 286, 140, 20);
		add(servicesComboBox);
		//
		
		// Quantity
		JLabel quantityLabel = new JLabel("Qty.");
		quantityLabel.setBounds(285, 267, 46, 14);
		add(quantityLabel);

		TextFieldPlaceHolder quantityTextField = new TextFieldPlaceHolder("quantity");
		quantityTextField.setBounds(316, 264, 58, 20);
		add(quantityTextField);
		//
		
		// Add product
		addShopOfferButton.addActionListener((e) -> {
			try {
				ShopOffer selectedOffer;
				int qty = Integer.parseInt(quantityTextField.getText());
				
				if (e.getActionCommand().contains(productRadioButton.getText()))
					selectedOffer = productsComboBox.getItemAt(productsComboBox.getSelectedIndex());
				else
					selectedOffer = servicesComboBox.getItemAt(servicesComboBox.getSelectedIndex());
				
				saleController.addSaleLine(selectedOffer, qty);
				offersTable.addOfferToTable(selectedOffer, qty);
				
				// Update the sale price information labels
				subtotalLabel.setText(saleController.getSaleSubtotal());
			
			} catch(NumberFormatException err) {
				ErrorAlert.show(this, "The Quantity must be valid!"); // TODO My exception ?
			}
		});
		addShopOfferButton.setBounds(285, 285, 126, 23);
		add(addShopOfferButton);
		//
		
		// Logout and Exit Buttons
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener((e) -> {
			LoginLogoutController logout = new LoginLogoutController(this, LoginView.class.getName());
			logout.logout();
		});
		logoutButton.setBounds(441, 256, 89, 23);
		add(logoutButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(441, 285, 89, 23);
		add(exitButton);
		
		JButton paymentButton = new JButton("Payment");
		paymentButton.addActionListener((e) -> {
			// TODO
		});
		paymentButton.setBounds(494, 134, 89, 23);
		add(paymentButton);
		
		// Show ui
		renderer.render();
	}
	
	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}
}
