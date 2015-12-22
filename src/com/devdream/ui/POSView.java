package com.devdream.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
import com.devdream.model.Product;
import com.devdream.model.Service;
import com.devdream.model.Shop;
import com.devdream.model.ShopOffer;
import com.devdream.ui.custom.Alert;
import com.devdream.ui.custom.MyComboBox;
import com.devdream.ui.custom.OfferSaleLinesTable;
import com.devdream.ui.custom.TextFieldPlaceHolder;

/**
 * Point of sale view.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class POSView extends View {

	private static final long serialVersionUID = -3842125628121409727L;

	//
	// Global
	private static final String CLIENT_ICON = "client.png";
	private static final String COMMERCIAL_ICON = "commercial.png";
	private static final String SALE_LINES_ICON = "salelines.png";
	private static final String OFFERS_ICON = "offers.png";
	private static final String TOTAL_SALE_ICON = "total.png";
	private static final String GOLD_CLIENT_ICON = "goldclient.png";

	//
	// Attributes
	private SaleController saleController;
	private LoginLogoutController logoutController;
	
	private Commercial loggedCommercial;
	private Client currentClient;
	private boolean isCurrentClientGold;
	private OfferSaleLinesTable offersTable;
	
	private MyComboBox<String, Client> clientsComboBox;
	private MyComboBox<Integer, Product> productsComboBox;
	private MyComboBox<Integer, Service> servicesComboBox;
	private JRadioButton productRadioButton;
	private JTextField totalLabel;
	private TextFieldPlaceHolder quantityTextField;
	private JLabel forGoldClientAlertLabel;
	private JLabel goldClientIconLabel;
	private JLabel clientCashLabel;
	private JLabel subtotalLabel;
	private JLabel taxLabel;
	private JButton addShopOfferButton;
	private JButton exitButton;
	private JButton deleteSelectedOfferButton;
	private JButton logoutButton;
	private JButton paymentButton;
	
	//
	// Constructors
	public POSView() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		saleController = new SaleController();
		logoutController = new LoginLogoutController(this, LoginView.class.getName());
		
		loadUI();
		
		loadListeners();

		getRenderer().render();
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
		clientCashLabel.setText(StringHelper.formatAmount(currentClient.getCash()));

		isCurrentClientGold = currentClient.isGoldClient();
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		goldClientIconLabel.setVisible(isCurrentClientGold);
	}

	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}

	@Override
	protected void loadUI() {
		// Logged commercial
		loggedCommercial = Intent.getInstance().getLogedCommercial();

		// Title
		JLabel posIconLabel = new JLabel("2Wheels POS");
		posIconLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		posIconLabel.setBounds(301, 11, 182, 25);
		getContentPane().add(posIconLabel);

		ButtonGroup radioButtonGroup = new ButtonGroup();

		paymentButton = new JButton("Process payment");
		paymentButton.setFont(new Font("Sitka Heading", Font.PLAIN, 18));
		paymentButton.setBackground(new Color(240, 248, 255));
		paymentButton.setBounds(271, 492, 244, 60);
		getContentPane().add(paymentButton);

		// Sale total information
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		forSubtotalLabel.setBounds(617, 474, 75, 14);
		getContentPane().add(forSubtotalLabel);

		subtotalLabel = new JLabel("0.00");
		subtotalLabel.setBounds(727, 476, 79, 14);
		getContentPane().add(subtotalLabel);

		JLabel forTaxLabel = new JLabel("Tax (" + Shop.VAT_TAX_PERCENTAGE + "%)");
		forTaxLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		forTaxLabel.setBounds(617, 499, 90, 14);
		getContentPane().add(forTaxLabel);

		taxLabel = new JLabel("0.00");
		taxLabel.setBounds(727, 501, 81, 14);
		getContentPane().add(taxLabel);

		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		forTotalLabel.setBounds(617, 524, 58, 28);
		getContentPane().add(forTotalLabel);

		totalLabel = new JTextField("0.00");
		totalLabel.setEditable(false);
		totalLabel.setFont(new Font("", Font.BOLD, 14));
		totalLabel.setBounds(727, 527, 58, 25);
		getContentPane().add(totalLabel);

		JLabel totalIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + TOTAL_SALE_ICON));
		totalIconLabel.setBounds(527, 480, 67, 60);
		getContentPane().add(totalIconLabel);

		JPanel commercialPanel = new JPanel();
		commercialPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commercialPanel.setBounds(10, 58, 357, 181);
		getContentPane().add(commercialPanel);
		commercialPanel.setLayout(null);

		// Commercial information
		JLabel lblCommercial = new JLabel("Commercial");
		lblCommercial.setBounds(10, 11, 105, 21);
		commercialPanel.add(lblCommercial);
		lblCommercial.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel commercialIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(125, 0, 58, 41);
		commercialPanel.add(commercialIconLabel);

		JLabel sessionByLabel = new JLabel(loggedCommercial.getName() + " " + loggedCommercial.getSurname());
		sessionByLabel.setBounds(135, 52, 155, 14);
		commercialPanel.add(sessionByLabel);
		sessionByLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

		JLabel forSessionByLabel = new JLabel("Session By:");
		forSessionByLabel.setBounds(10, 52, 75, 14);
		commercialPanel.add(forSessionByLabel);
		forSessionByLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel forCommercialPointsLabel = new JLabel("Commercial points:");
		forCommercialPointsLabel.setBounds(10, 77, 117, 14);
		commercialPanel.add(forCommercialPointsLabel);
		forCommercialPointsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel commercialPointsLabel = new JLabel(Integer.toString(loggedCommercial.getEarnedPoints()));
		commercialPointsLabel.setBounds(145, 77, 82, 14);
		commercialPanel.add(commercialPointsLabel);
		commercialPointsLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(10, 130, 105, 29);
		commercialPanel.add(logoutButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(139, 130, 117, 29);
		commercialPanel.add(exitButton);

		JPanel clientPanel = new JPanel();
		clientPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientPanel.setBounds(10, 259, 357, 203);
		getContentPane().add(clientPanel);
		clientPanel.setLayout(null);

		// Client information
		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(10, 11, 75, 21);
		clientPanel.add(lblClient);
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel clientIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + CLIENT_ICON));
		clientIconLabel.setBounds(56, -1, 59, 40);
		clientPanel.add(clientIconLabel);

		clientsComboBox = new MyComboBox<String, Client>(Intent.getInstance().getClients());
		clientsComboBox.setBounds(133, 50, 150, 21);
		clientPanel.add(clientsComboBox);

		JLabel forClientSelectionLabel = new JLabel("Select the client:");
		forClientSelectionLabel.setBounds(10, 50, 105, 21);
		clientPanel.add(forClientSelectionLabel);
		forClientSelectionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel forClientCashLabel = new JLabel("Client cash:");
		forClientCashLabel.setBounds(10, 93, 75, 14);
		clientPanel.add(forClientCashLabel);
		forClientCashLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		clientCashLabel = new JLabel();
		clientCashLabel.setBounds(95, 94, 98, 14);
		clientPanel.add(clientCashLabel);

		forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setBounds(246, 119, 89, 21);
		clientPanel.add(forGoldClientAlertLabel);
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		forGoldClientAlertLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));

		goldClientIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + GOLD_CLIENT_ICON));
		goldClientIconLabel.setBounds(256, 71, 58, 41);
		clientPanel.add(goldClientIconLabel);

		JButton newClientButton = new JButton("New Client");
		newClientButton.setBounds(10, 159, 112, 33);
		clientPanel.add(newClientButton);

		JButton btnNewButton = new JButton("Charge cash");
		btnNewButton.setBounds(133, 159, 112, 33);
		clientPanel.add(btnNewButton);
		btnNewButton.addActionListener((e) -> new ChargeCashView(currentClient));
		newClientButton.addActionListener((e) -> new NewClientView());
		goldClientIconLabel.setVisible(isCurrentClientGold);
		
		setCurrentClient((Client) clientsComboBox.getSelectedItem());
		clientsComboBox.addActionListener((e) -> setCurrentClient((Client) clientsComboBox.getSelectedItem()));

		JPanel offerPanel = new JPanel();
		offerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		offerPanel.setBounds(393, 58, 418, 405);
		getContentPane().add(offerPanel);
		offerPanel.setLayout(null);

		deleteSelectedOfferButton = new JButton("Delete selected offer");
		deleteSelectedOfferButton.setBounds(144, 210, 165, 23);
		offerPanel.add(deleteSelectedOfferButton);

		addShopOfferButton = new JButton("Add product");
		addShopOfferButton.setBounds(144, 176, 165, 23);
		offerPanel.add(addShopOfferButton);

		quantityTextField = new TextFieldPlaceHolder("Quantity");
		quantityTextField.setBounds(144, 139, 58, 20);
		offerPanel.add(quantityTextField);

		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(49, 141, 77, 14);
		offerPanel.add(quantityLabel);
		quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JRadioButton serviceRadioButton = new JRadioButton("Service");
		serviceRadioButton.setBounds(49, 94, 77, 23);
		offerPanel.add(serviceRadioButton);
		serviceRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		serviceRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add service"));
		radioButtonGroup.add(serviceRadioButton);

		JLabel productPrice = new JLabel("0.00");
		productPrice.setBounds(333, 58, 58, 14);
		offerPanel.add(productPrice);

		productsComboBox = new MyComboBox<Integer, Product>(Intent.getInstance().getProducts());
		productsComboBox.addActionListener((e) ->
			productPrice.setText(StringHelper.formatAmount(((Product) productsComboBox.getSelectedItem()).getPrice())));
		productsComboBox.setBounds(144, 58, 165, 20);
		offerPanel.add(productsComboBox);

		productPrice.setText(StringHelper.formatAmount(((Product) productsComboBox.getSelectedItem()).getPrice()));
		
		JLabel servicePrice = new JLabel("0.00");
		servicePrice.setBounds(333, 99, 58, 14);
		offerPanel.add(servicePrice);
		
		servicesComboBox = new MyComboBox<Integer, Service>(Intent.getInstance().getServices());
		servicesComboBox.addActionListener((e) ->
			servicePrice.setText(StringHelper.formatAmount(((Service) servicesComboBox.getSelectedItem()).getPrice())));
		servicesComboBox.setBounds(144, 94, 165, 20);
		offerPanel.add(servicesComboBox);

		servicePrice.setText(StringHelper.formatAmount(((Service) servicesComboBox.getSelectedItem()).getPrice()));

		productRadioButton = new JRadioButton("Product");
		productRadioButton.setBounds(49, 58, 77, 23);
		offerPanel.add(productRadioButton);
		productRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		productRadioButton.addActionListener((e) -> addShopOfferButton.setText("Add product"));
		productRadioButton.setSelected(true);
		radioButtonGroup.add(productRadioButton);

		// Shop offers selection
		JLabel addOfferLabel = new JLabel("Add offer");
		addOfferLabel.setBounds(21, 11, 105, 21);
		offerPanel.add(addOfferLabel);
		addOfferLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel offerIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + OFFERS_ICON));
		offerIconLabel.setBounds(110, 0, 46, 41);
		offerPanel.add(offerIconLabel);

		JScrollPane productsTableScrollPane = new JScrollPane();
		productsTableScrollPane.setBounds(10, 244, 398, 150);
		offerPanel.add(productsTableScrollPane);

		offersTable = new OfferSaleLinesTable(saleController.getSaleLines());
		productsTableScrollPane.setViewportView(offersTable);

		// Sale lines
		JLabel productsTableLabel = new JLabel("Sale lines");
		productsTableLabel.setBounds(10, 212, 105, 21);
		offerPanel.add(productsTableLabel);
		productsTableLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel saleLinesIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + SALE_LINES_ICON));
		saleLinesIconLabel.setBounds(75, 210, 35, 33);
		offerPanel.add(saleLinesIconLabel);
	}

	@Override
	protected void loadListeners() {
		exitButton.addActionListener(new OnExitAction(this));
		logoutButton.addActionListener((e) -> logoutController.logout());
		
		paymentButton.addActionListener((e) -> {
			PaymentController paymentController = new PaymentController(this, BillView.class.getName());
			try {
				paymentController.processPayment(saleController.getSale(), (Client) clientsComboBox.getSelectedItem(),
						isCurrentClientGold, loggedCommercial);
			} catch (EmptyPaymentException | CantAffordException err) {
				Alert.showError(this, err.getMessage());
			}
		});
		
		addShopOfferButton.addActionListener((e) -> {
			try {
				ShopOffer selectedOffer;
				int qty = Integer.parseInt(quantityTextField.getText());
				if (e.getActionCommand().contains(productRadioButton.getText().toLowerCase())) {
					selectedOffer = (Product) productsComboBox.getSelectedItem();
				} else {
					selectedOffer = (Service) servicesComboBox.getSelectedItem();
				}
				// offersTable.getSale
				saleController.addSaleLine(selectedOffer, qty);

				updateSale();
			} catch (NumberFormatException err) {
				Alert.showError(this, "The Quantity is not valid!");
			}
		});
		
		deleteSelectedOfferButton.addActionListener((e) -> {
			if (offersTable.getSelectedRow() > -1) {
				saleController.deleteSaleSaleLine(offersTable.getSelectedRow());
				updateSale();
			} else {
				Alert.showError(this, "There is no offer selected!");
			}
		});
	}
	
}
