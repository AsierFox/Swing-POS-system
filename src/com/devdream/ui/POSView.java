package com.devdream.ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.devdream.controller.LoginLogoutController;
import com.devdream.controller.OnExitAction;
import com.devdream.controller.PaymentController;
import com.devdream.controller.SaleController;
import com.devdream.data.AppData;
import com.devdream.data.bind.Intent;
import com.devdream.exception.CantAffordException;
import com.devdream.exception.EmptyPaymentException;
import com.devdream.helper.StringHelper;
import com.devdream.model.Client;
import com.devdream.model.Commercial;
import com.devdream.model.GoldClient;
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.model.ShopOffer;
import com.devdream.ui.custom.Alert;
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
	// Global
	private static final String CLIENT_ICON = "client.png";
	private static final String COMMERCIAL_ICON = "commercial.png";
	private static final String SALE_LINES_ICON = "salelines.png";
	private static final String OFFERS_ICON = "offers.png";
	private static final String TOTAL_SALE_ICON = "total.png";
	private static final String GOLD_CLIENT_ICON = "goldclient.png";
	private static final String DISCOUNT_ICON = "discount.png";
	
	//
	// Attributes
	private Commercial loggedCommercial;
	private Client currentClient;
	private boolean isCurrentClientGold;
	private SaleController saleController;
	private ShopOfferTable offersTable;
	private MyComboBox<String, Client> clientsComboBox;
	private JLabel forGoldClientAlertLabel;
	private JLabel goldClientIconLabel;
	private JLabel discountIconLabel;
	private JLabel forDiscountLabel;
	private JLabel discountLabel;
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
		
		// logged commercial
		loggedCommercial = Intent.getInstance().getLogedCommercial();
		
		// Sale Controller
		saleController = new SaleController();
		
		// Title
		JLabel posIconLabel = new JLabel("2Wheels POS");
		posIconLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		posIconLabel.setBounds(301, 11, 159, 25);
		getContentPane().add(posIconLabel);
		
		// Commercial information
		JLabel lblCommercial = new JLabel("Commercial");
		lblCommercial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCommercial.setBounds(23, 69, 105, 21);
		getContentPane().add(lblCommercial);
		
		JLabel commercialIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(121, 58, 58, 41);
		getContentPane().add(commercialIconLabel);
		
		JLabel forSessionByLabel = new JLabel("Session By:");
		forSessionByLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forSessionByLabel.setBounds(23, 117, 75, 14);
		getContentPane().add(forSessionByLabel);
		
		JLabel sessionByLabel = new JLabel(loggedCommercial.getName());
		sessionByLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		sessionByLabel.setBounds(160, 117, 82, 14);
		getContentPane().add(sessionByLabel);
		
		JLabel forCommercialPointsLabel = new JLabel("Commercial points:");
		forCommercialPointsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialPointsLabel.setBounds(23, 151, 117, 14);
		getContentPane().add(forCommercialPointsLabel);
		
		JLabel commercialPointsLabel = new JLabel(Integer.toString(loggedCommercial.getEarnedPoints()));
		commercialPointsLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		commercialPointsLabel.setBounds(160, 151, 82, 14);
		getContentPane().add(commercialPointsLabel);
		
		// Logout and Exit Buttons
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener((e) -> {
			LoginLogoutController logout = new LoginLogoutController(this, LoginView.class.getName());
			logout.logout();
		});
		logoutButton.setBounds(17, 198, 111, 21);
		getContentPane().add(logoutButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new OnExitAction(this));
		exitButton.setBounds(158, 198, 111, 21);
		getContentPane().add(exitButton);

		// Client information
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClient.setBounds(17, 259, 105, 21);
		getContentPane().add(lblClient);

		JLabel clientIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + CLIENT_ICON));
		clientIconLabel.setBounds(80, 250, 75, 40);
		getContentPane().add(clientIconLabel);
		
		JLabel forClientCashLabel = new JLabel("Client cash:");
		forClientCashLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientCashLabel.setBounds(23, 362, 75, 14);
		getContentPane().add(forClientCashLabel);
		
		clientCashLabel = new JLabel();
		clientCashLabel.setBounds(116, 362, 98, 14);
		getContentPane().add(clientCashLabel);
		
		JLabel forClientSelectionLabel = new JLabel("Select the client:");
		forClientSelectionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientSelectionLabel.setBounds(17, 307, 105, 21);
		getContentPane().add(forClientSelectionLabel);
		
		forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		forGoldClientAlertLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));
		forGoldClientAlertLabel.setBounds(286, 340, 89, 21);
		getContentPane().add(forGoldClientAlertLabel);
		
		goldClientIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + GOLD_CLIENT_ICON));
		goldClientIconLabel.setVisible(isCurrentClientGold);
		goldClientIconLabel.setBounds(292, 295, 58, 41);
		getContentPane().add(goldClientIconLabel);
		
		discountIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + DISCOUNT_ICON));
		discountIconLabel.setVisible(isCurrentClientGold);
		discountIconLabel.setBounds(301, 482, 59, 49);
		getContentPane().add(discountIconLabel);
		
		forDiscountLabel = new JLabel("Discount");
		forDiscountLabel.setVisible(isCurrentClientGold);
		forDiscountLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 15));
		forDiscountLabel.setBounds(216, 471, 105, 25);
		getContentPane().add(forDiscountLabel);		
		discountLabel = new JLabel(GoldClient.DISCOUNT_PERCENTAGE + " %");
		discountLabel.setVisible(isCurrentClientGold);
		discountLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 15));
		discountLabel.setBounds(226, 515, 82, 25);
		getContentPane().add(discountLabel);
		
		clientsComboBox = new MyComboBox<String, Client>(Intent.getInstance().getClients());
		setCurrentClient((Client) clientsComboBox.getSelectedItem());
		clientsComboBox.addActionListener((e) -> setCurrentClient((Client) clientsComboBox.getSelectedItem()));
		clientsComboBox.setBounds(133, 307, 150, 21);
		getContentPane().add(clientsComboBox);
		
		JButton newClientButton = new JButton("New Client");
		newClientButton.addActionListener((e) -> new NewClientView());
		newClientButton.setBounds(17, 416, 112, 33);
		getContentPane().add(newClientButton);

		JButton btnNewButton = new JButton("Charge cash");
		btnNewButton.addActionListener((e) -> new ChargeCashView(currentClient));
		btnNewButton.setBounds(157, 416, 112, 33);
		getContentPane().add(btnNewButton);
		

		// Shop offers selection
		JLabel addOfferLabel = new JLabel("Add offer");
		addOfferLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addOfferLabel.setBounds(407, 69, 105, 21);
		getContentPane().add(addOfferLabel);
		
		JLabel offerIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + OFFERS_ICON));
		offerIconLabel.setBounds(496, 58, 46, 41);
		getContentPane().add(offerIconLabel);
		
		JButton addShopOfferButton = new JButton("Add product");
		
		JRadioButton productRadioButton = new JRadioButton("Product");
		productRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		productRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add product"));
		productRadioButton.setSelected(true);
		productRadioButton.setBounds(407, 119, 77, 23);
		getContentPane().add(productRadioButton);
		
		JRadioButton serviceRadioButton = new JRadioButton("Service");
		serviceRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		serviceRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add service"));
		serviceRadioButton.setBounds(407, 162, 77, 23);
		getContentPane().add(serviceRadioButton);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(productRadioButton);
		radioButtonGroup.add(serviceRadioButton);
		
		MyComboBox<Integer, Product> productsComboBox = new MyComboBox<Integer, Product>(Intent.getInstance().getProducts());
		productsComboBox.setBounds(503, 122, 140, 20);
		getContentPane().add(productsComboBox);
		
		MyComboBox<Integer, Service> servicesComboBox = new MyComboBox<Integer, Service>(Intent.getInstance().getServices());
		servicesComboBox.setBounds(503, 162, 140, 20);
		getContentPane().add(servicesComboBox);
		
		JLabel quantityLabel = new JLabel("Qty.");
		quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		quantityLabel.setBounds(412, 209, 46, 14);
		getContentPane().add(quantityLabel);

		TextFieldPlaceHolder quantityTextField = new TextFieldPlaceHolder("Quantity");
		quantityTextField.setBounds(468, 206, 58, 20);
		getContentPane().add(quantityTextField);
		
		addShopOfferButton.addActionListener((e) -> {
			try {
				ShopOffer selectedOffer;
				int qty = Integer.parseInt(quantityTextField.getText());
				if (e.getActionCommand().contains(productRadioButton.getText().toLowerCase())) {
					selectedOffer = (Product) productsComboBox.getSelectedItem();
				} else {
					selectedOffer = (Service) servicesComboBox.getSelectedItem();
				}
				saleController.addSaleLine(selectedOffer, qty);
				
				updateSale();
			} catch(NumberFormatException err) {
				Alert.showError(this, "The Quantity is not valid!");
			}
		});
		addShopOfferButton.setBounds(536, 204, 125, 23);
		getContentPane().add(addShopOfferButton);
		
		// Sale lines
		JLabel productsTableLabel = new JLabel("Sale lines");
		productsTableLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		productsTableLabel.setBounds(399, 259, 105, 21);
		getContentPane().add(productsTableLabel);

		JLabel saleLinesIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + SALE_LINES_ICON));
		saleLinesIconLabel.setBounds(469, 250, 35, 33);
		getContentPane().add(saleLinesIconLabel);
		
		JScrollPane productsTableScrollPane = new JScrollPane();
		productsTableScrollPane.setBounds(407, 299, 398, 150);
		getContentPane().add(productsTableScrollPane);
		
		offersTable = new ShopOfferTable(saleController.getSaleLines());
		productsTableScrollPane.setViewportView(offersTable);
		
		JButton deleteSelectedOfferButton = new JButton("Delete selected offer");
		deleteSelectedOfferButton.addActionListener((e) -> {
			if (offersTable.getSelectedRow() > -1) {
				saleController.deleteSaleSaleLine(offersTable.getSelectedRow());
				updateSale();
			} else {
				Alert.showError(this, "There is no offer selected!");
			}
		});
		deleteSelectedOfferButton.setBounds(617, 468, 159, 33);
		getContentPane().add(deleteSelectedOfferButton);
		
		JButton paymentButton = new JButton("Process payment");
		paymentButton.addActionListener((e) -> {
			PaymentController paymentController = new PaymentController(this, BillView.class.getName());
			try {
				paymentController.processPayment(saleController.getSale(),
					(Client) clientsComboBox.getSelectedItem(),
					isCurrentClientGold,
					loggedCommercial);
			} catch(EmptyPaymentException | CantAffordException err) {
				Alert.showError(this, err.getMessage());
			}
		});
		paymentButton.setBounds(617, 512, 159, 33);
		getContentPane().add(paymentButton);
		
		// Sale total information
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		forSubtotalLabel.setBounds(373, 468, 75, 14);
		getContentPane().add(forSubtotalLabel);
		
		subtotalLabel = new JLabel("0");
		subtotalLabel.setBounds(473, 470, 79, 14);
		getContentPane().add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		forTaxLabel.setBounds(373, 493, 52, 14);
		getContentPane().add(forTaxLabel);
		
		taxLabel = new JLabel("0");
		taxLabel.setBounds(473, 495, 81, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		forTotalLabel.setBounds(373, 518, 58, 14);
		getContentPane().add(forTotalLabel);
		
		totalLabel = new JLabel("0");
		totalLabel.setBounds(473, 520, 81, 14);
		getContentPane().add(totalLabel);
		
		JLabel totalIconLabel = new JLabel(renderer.renderImage(AppData.ImagePath.POS_ICON + TOTAL_SALE_ICON));
		totalIconLabel.setBounds(540, 472, 67, 60);
		getContentPane().add(totalIconLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		panel.setBounds(10, 58, 357, 181);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		panel_1.setBounds(10, 259, 357, 203);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		panel_2.setBounds(393, 58, 412, 181);
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		panel_3.setBounds(393, 259, 419, 203);
		getContentPane().add(panel_3);
		
		renderer.render();
	}
	
	//
	// Methods
	private void updateSale() {
		offersTable.update();
		
		subtotalLabel.setText(saleController.getSaleSubtotal());
		taxLabel.setText(saleController.getSaleTax());
		totalLabel.setText(saleController.getSaleTotal());
	}
	
	public void setCurrentClient(Client client) {
		currentClient = client;
		Intent.getInstance().setActualClient(currentClient);
		isCurrentClientGold = currentClient.isGoldClient();
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		goldClientIconLabel.setVisible(isCurrentClientGold);
		discountIconLabel.setVisible(isCurrentClientGold);
		forDiscountLabel.setVisible(isCurrentClientGold);
		discountLabel.setVisible(isCurrentClientGold);
		clientCashLabel.setText(StringHelper.formatAmount(currentClient.getCash()));
	}
	
	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}
}
