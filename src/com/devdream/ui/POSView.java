package com.devdream.ui;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.controller.PaymentController;
import com.devdream.controller.SaleController;
import com.devdream.data.bind.Intent;
import com.devdream.exception.CantAffordException;
import com.devdream.exception.EmptyPaymentException;
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
 * Point of sale view.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class POSView extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -3842125628121409727L;
	
	//
	// Attributes
	private Commercial logedCommercial;
	private Client currentClient;
	private ShopOfferTable offersTable;
	private MyComboBox<Client> clientsComboBox;
	private JLabel forGoldClientAlertLabel;
	private JLabel clientCashLabel;
	private JLabel subtotalLabel;
	private JLabel taxLabel;
	private JLabel totalLabel;
	
	//
	// Constructors
	public POSView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		// loged commercial
		logedCommercial = Intent.getInstance().getLogedCommercial();
		
		// Sale Controller
		SaleController saleController = new SaleController();
		
		// Commercial information
		JLabel testLabel = new JLabel("Session By: ");
		testLabel.setText(testLabel.getText() + Intent.getInstance().getLogedCommercial().getName());
		testLabel.setBounds(0, 0, 138, 21);
		getContentPane().add(testLabel);
		
		JLabel forCommercialPointsLabel = new JLabel("Commercial points");
		forCommercialPointsLabel.setBounds(484, 84, 105, 14);
		getContentPane().add(forCommercialPointsLabel);
		
		JLabel commercialPointsLabel = new JLabel(Integer.toString(logedCommercial.getEarnedPoints()));
		commercialPointsLabel.setBounds(484, 109, 46, 14);
		getContentPane().add(commercialPointsLabel);
		//

		// Client cash info
		JLabel forClientCashLabel = new JLabel("Client cash");
		forClientCashLabel.setBounds(545, 33, 75, 14);
		getContentPane().add(forClientCashLabel);
		
		clientCashLabel = new JLabel("");
		clientCashLabel.setBounds(630, 33, 46, 14);
		getContentPane().add(clientCashLabel);
		//
		
		// Client Label + ComboBox + Gold client Label
		JLabel clientLabel = new JLabel("Select the client:");
		clientLabel.setBounds(10, 30, 105, 21);
		getContentPane().add(clientLabel);
		
		forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setVisible(false);
		forGoldClientAlertLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));
		forGoldClientAlertLabel.setBounds(285, 30, 89, 21);
		getContentPane().add(forGoldClientAlertLabel);
		
		clientsComboBox = new MyComboBox<Client>(Intent.getInstance().getClients());
		// Set default combo box selection
		setCurrentClient(0);
		clientsComboBox.addActionListener((e) -> setCurrentClient(clientsComboBox.getSelectedIndex()));
		clientsComboBox.setBounds(125, 30, 150, 21);
		getContentPane().add(clientsComboBox);
		//

		// New client button
		JButton newClientButton = new JButton("New Client");
		newClientButton.addActionListener((e) -> new NewClientView());
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
		
		offersTable = new ShopOfferTable(saleController.getSaleLines());
		productsTableScrollPane.setViewportView(offersTable);
		//
		
		// Sale total information
		JLabel forSubTotalLabel = new JLabel("Sub Total");
		forSubTotalLabel.setBounds(478, 168, 57, 14);
		getContentPane().add(forSubTotalLabel);
		
		subtotalLabel = new JLabel("0");
		subtotalLabel.setBounds(545, 168, 46, 14);
		getContentPane().add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setBounds(478, 193, 46, 14);
		getContentPane().add(forTaxLabel);
		
		taxLabel = new JLabel("0");
		taxLabel.setBounds(543, 193, 46, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setBounds(478, 218, 46, 14);
		getContentPane().add(forTotalLabel);
		
		totalLabel = new JLabel("0");
		totalLabel.setBounds(543, 218, 77, 14);
		getContentPane().add(totalLabel);
		//
		
		// Select Product or Service Label and Radio Buttons
		JButton addShopOfferButton = new JButton("Add product");
		
		JRadioButton productRadioButton = new JRadioButton("product");
		productRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add product"));
		productRadioButton.setSelected(true);
		productRadioButton.setBounds(29, 285, 77, 23);
		getContentPane().add(productRadioButton);
		
		JRadioButton serviceRadioButton = new JRadioButton("service");
		serviceRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add service"));
		serviceRadioButton.setBounds(29, 322, 77, 23);
		getContentPane().add(serviceRadioButton);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(productRadioButton);
		radioButtonGroup.add(serviceRadioButton);
		//
		
		// Shop offers ComboBox
		JLabel forSelectShopOfferLabel = new JLabel("Select: ");
		forSelectShopOfferLabel.setBounds(29, 264, 58, 14);
		getContentPane().add(forSelectShopOfferLabel);
		
		MyComboBox<Product> productsComboBox = new MyComboBox<Product>(Intent.getInstance().getProducts());
		productsComboBox.setBounds(125, 288, 140, 20);
		getContentPane().add(productsComboBox);
		
		MyComboBox<Service> servicesComboBox = new MyComboBox<Service>(Intent.getInstance().getServices());
		servicesComboBox.setBounds(125, 322, 140, 20);
		getContentPane().add(servicesComboBox);
		//
		
		// Quantity
		JLabel quantityLabel = new JLabel("Qty.");
		quantityLabel.setBounds(285, 305, 46, 14);
		getContentPane().add(quantityLabel);

		TextFieldPlaceHolder quantityTextField = new TextFieldPlaceHolder("quantity");
		quantityTextField.setBounds(316, 302, 58, 20);
		getContentPane().add(quantityTextField);
		//
		
		// Add offer
		addShopOfferButton.addActionListener((e) -> {
			try {
				ShopOffer selectedOffer;
				int qty = Integer.parseInt(quantityTextField.getText());
				
				if (e.getActionCommand().contains(productRadioButton.getText()))
					selectedOffer = productsComboBox.getItemAt(productsComboBox.getSelectedIndex());
				else
					selectedOffer = servicesComboBox.getItemAt(servicesComboBox.getSelectedIndex());
				
				saleController.addSaleLine(selectedOffer, qty);
				
				updateSale(saleController.getSaleSubtotal(),
						saleController.getSaleTax(),
						saleController.getSaleTotal());
			
			} catch(NumberFormatException err) {
				ErrorAlert.show(this, "The Quantity is not valid!");
			}
		});
		addShopOfferButton.setBounds(379, 285, 151, 23);
		getContentPane().add(addShopOfferButton);
		//
		
		// Delete offer
		JButton deleteSelectedOfferButton = new JButton("Delete selected offer");
		deleteSelectedOfferButton.addActionListener((e) -> {
			if (offersTable.getSelectedRow() > -1) {
				saleController.deleteSaleSaleLine(offersTable.getSelectedRow());
				updateSale(saleController.getSaleSubtotal(),
						saleController.getSaleTax(),
						saleController.getSaleTotal());
			}
			else {
				ErrorAlert.show(this, "There is no offer selected!");
			}
		});
		deleteSelectedOfferButton.setBounds(379, 322, 151, 23);
		getContentPane().add(deleteSelectedOfferButton);
		//
		
		// Payment button
		JButton paymentButton = new JButton("Payment");
		paymentButton.addActionListener((e) -> {
			PaymentController paymentController = new PaymentController(this, BillView.class.getName());
			try {
				paymentController.processPayment(saleController.getSale(), currentClient, logedCommercial);
			} catch(EmptyPaymentException | CantAffordException err) {
				ErrorAlert.show(this, err.getMessage());
			}
		});
		paymentButton.setBounds(488, 243, 89, 23);
		getContentPane().add(paymentButton);
		//
		
		// Logout and Exit Buttons
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener((e) -> {
			LoginLogoutController logout = new LoginLogoutController(this, LoginView.class.getName());
			logout.logout();
		});
		logoutButton.setBounds(531, 373, 89, 23);
		getContentPane().add(logoutButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(531, 407, 89, 23);
		getContentPane().add(exitButton);
		
		// Show ui
		renderer.render();
	}
	
	private void updateSale(String saleSubtotal, String saleTax, String saleTotal) {
		offersTable.update();
		
		subtotalLabel.setText(saleSubtotal);
		taxLabel.setText(saleTax);
		totalLabel.setText(saleTotal);
	}

	//
	// Methods
	public void setCurrentClient(int selectedComboBoxIndex) {
		currentClient = Intent.getInstance().getClients().get(selectedComboBoxIndex);
		Intent.getInstance().setActualClient(currentClient);
		forGoldClientAlertLabel.setVisible(currentClient.isGoldClient());
		clientCashLabel.setText(Double.toString(currentClient.getSubscriberCard().getCash()));
	}
	
	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}
	
}
