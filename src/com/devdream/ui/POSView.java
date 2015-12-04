package com.devdream.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.devdream.controller.OnExitAction;
import com.devdream.data.bind.Intent;
import com.devdream.model.Client;
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.model.ShopOffer;
import com.devdream.ui.custom.ErrorAlert;
import com.devdream.ui.custom.MyComboBox;
import com.devdream.ui.custom.TextFieldPlaceHolder;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * TODO Description
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class POSView extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -3842125628121409727L;
	
	private JTable offersTable;
	private DefaultTableModel offersTableModel;
	
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
		forCommercialPointsLabel.setBounds(484, 84, 77, 14);
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
		
		MyComboBox<Client> clientsComboBox = new MyComboBox<Client>(Intent.getInstance().getClients());
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
			NewClientView newClientView = new NewClientView();
		});
		newClientButton.setBounds(379, 29, 112, 23);
		getContentPane().add(newClientButton);
		//
		
		// Products table
		JLabel productsTableLabel = new JLabel("Products");
		productsTableLabel.setBounds(70, 63, 68, 21);
		getContentPane().add(productsTableLabel);
		
		JScrollPane roductsTableScrollPane = new JScrollPane();
		roductsTableScrollPane.setBounds(70, 95, 398, 150);
		getContentPane().add(roductsTableScrollPane);

		offersTableModel = new DefaultTableModel();
		offersTable = new JTable(offersTableModel);
		setOffersTableHeader();
		roductsTableScrollPane.setViewportView(offersTable);
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
		TextFieldPlaceHolder quantityTextField = new TextFieldPlaceHolder("quantity");
		quantityTextField.setBounds(316, 264, 58, 20);
		getContentPane().add(quantityTextField);
		quantityTextField.setColumns(10);
		
		JLabel quantityLabel = new JLabel("Qty.");
		quantityLabel.setBounds(285, 267, 46, 14);
		getContentPane().add(quantityLabel);
		//
		
		// Add product
		addShopOfferButton.addActionListener((e) -> {
			try {
				int qty = Integer.parseInt(quantityTextField.getText().trim()); // TODO Control range
				
				
				if (e.getActionCommand().contains(productRadioButton.getText())) {
					//addOfferToTable(offer, qty);
				}
				else {
					// TODO insert service to the table
				}
			} catch(NumberFormatException err) {
				ErrorAlert.show(this, "The Quantity must be valid!"); // TODO My exception?
			}
		});
		addShopOfferButton.setBounds(285, 285, 126, 23);
		getContentPane().add(addShopOfferButton);
		//

		// Exit Button
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(441, 285, 89, 23);
		getContentPane().add(exitButton);
		
		// Show ui
		renderer.render();
	}
	
	private void setOffersTableHeader() {
		offersTableModel.addColumn("ID");
		offersTableModel.addColumn("Name");
		offersTableModel.addColumn("Desc.");
		offersTableModel.addColumn("Unit price");
		offersTableModel.addColumn("Qty.");
	}
	
	private void addOfferToTable(ShopOffer offer, int qty) {
		Vector<String> row = new Vector<String>();
		row.addElement(Integer.toString(offer.getID()));
		row.addElement(offer.getName());
		row.addElement(offer.getDescription());
		row.addElement(Float.toString(offer.getPrice()));
		row.addElement(Integer.toString(qty));
		offersTableModel.addRow(row);
	}
	
}