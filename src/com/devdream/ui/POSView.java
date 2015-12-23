package com.devdream.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractButton;
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
import com.devdream.model.GoldClient;
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
	private static final String CLIENT_ICON = "clients.png";
	private static final String COMMERCIAL_ICON = "commercial.png";
	private static final String SALE_LINES_ICON = "salelines.png";
	private static final String OFFERS_ICON = "offers.png";
	private static final String ADD_OFFER_ICON = "plus.png";
	private static final String REMOVE_OFFER_ICON = "minus.png";
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
	private JRadioButton serviceRadioButton;
	private JTextField totalLabel;
	private TextFieldPlaceHolder quantityTextField;
	private JLabel forGoldClientAlertLabel;
	private JLabel forClientDiscountLabel;
	private JLabel clientDiscountLabel;
	private JLabel goldClientIconLabel;
	private JLabel clientCashLabel;
	private JLabel productPrice;
	private JLabel servicePrice;
	private JLabel subtotalLabel;
	private JLabel taxLabel;
	private JButton addOfferButton;
	private JButton deleteSelectedOfferButton;
	private JButton logoutButton;
	private JButton newClientButton;
	private JButton chargeCashButton;
	private JButton paymentButton;
	private JButton exitButton;

	//
	// Constructors
	public POSView() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		loggedCommercial = Intent.getInstance().getLogedCommercial();
		
		saleController = new SaleController();
		logoutController = new LoginLogoutController(this, LoginView.class.getName());

		loadUI();

		loadListeners();

		getRenderer().render();
	}

	//
	// Methods
	/** Updates the sale information. */
	private void updateSale() {
		offersTable.update();

		subtotalLabel.setText(saleController.getSaleSubtotal());
		taxLabel.setText(saleController.getSaleTax());
		totalLabel.setText(saleController.getSaleTotal());
	}

	/** Sets the current selected client information. */
	public void setCurrentClient(Client client) {
		currentClient = client;
		isCurrentClientGold = currentClient.isGoldClient();
		
		Intent.getInstance().setActualClient(currentClient);
		clientCashLabel.setText(currentClient.getFormattedCash());
		
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		goldClientIconLabel.setVisible(isCurrentClientGold);
		forClientDiscountLabel.setVisible(isCurrentClientGold);
		goldClientIconLabel.setVisible(isCurrentClientGold);
		clientDiscountLabel.setVisible(isCurrentClientGold);
	}

	@Override
	public boolean isFocused() {
		clientsComboBox.update();
		return super.isFocused();
	}

	@Override
	protected void loadUI() {
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

		JLabel commercialIconLabel = new JLabel(
				getRenderer().renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(125, 0, 58, 41);
		commercialPanel.add(commercialIconLabel);

		JLabel sessionByLabel = new JLabel(loggedCommercial.getName() + " " + loggedCommercial.getSurname());
		sessionByLabel.setBounds(135, 52, 155, 14);
		commercialPanel.add(sessionByLabel);
		sessionByLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

		JLabel forSessionByLabel = new JLabel("Session by");
		forSessionByLabel.setBounds(10, 52, 75, 14);
		commercialPanel.add(forSessionByLabel);
		forSessionByLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel forCommercialPointsLabel = new JLabel("Earned points");
		forCommercialPointsLabel.setBounds(10, 77, 117, 14);
		commercialPanel.add(forCommercialPointsLabel);
		forCommercialPointsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel commercialPointsLabel = new JLabel(Integer.toString(loggedCommercial.getEarnedPoints()));
		commercialPointsLabel.setBounds(135, 77, 82, 14);
		commercialPanel.add(commercialPointsLabel);
		commercialPointsLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(10, 141, 105, 29);
		commercialPanel.add(logoutButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(139, 141, 117, 29);
		commercialPanel.add(exitButton);

		JLabel forCommercialSalaryLabel = new JLabel("Salary");
		forCommercialSalaryLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialSalaryLabel.setBounds(10, 105, 117, 14);
		commercialPanel.add(forCommercialSalaryLabel);

		JLabel commercialSalaryLabel = new JLabel(loggedCommercial.getFormattedSalary());
		commercialSalaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		commercialSalaryLabel.setBounds(135, 102, 82, 14);
		commercialPanel.add(commercialSalaryLabel);

		JPanel clientPanel = new JPanel();
		clientPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientPanel.setBounds(10, 259, 357, 203);
		getContentPane().add(clientPanel);
		clientPanel.setLayout(null);

		// Client information
		JLabel forClientLabel = new JLabel("Client");
		forClientLabel.setBounds(10, 11, 75, 21);
		clientPanel.add(forClientLabel);
		forClientLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel clientIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + CLIENT_ICON));
		clientIconLabel.setBounds(56, -1, 59, 40);
		clientPanel.add(clientIconLabel);

		clientsComboBox = new MyComboBox<String, Client>(Intent.getInstance().getClients());
		clientsComboBox.setBounds(133, 50, 150, 21);
		clientPanel.add(clientsComboBox);

		JLabel forClientSelectionLabel = new JLabel("Select the client");
		forClientSelectionLabel.setBounds(10, 50, 105, 21);
		clientPanel.add(forClientSelectionLabel);
		forClientSelectionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel forClientCashLabel = new JLabel("Client cash");
		forClientCashLabel.setBounds(10, 93, 75, 14);
		clientPanel.add(forClientCashLabel);
		forClientCashLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		clientCashLabel = new JLabel();
		clientCashLabel.setBounds(133, 94, 98, 14);
		clientPanel.add(clientCashLabel);

		forGoldClientAlertLabel = new JLabel("Gold client");
		forGoldClientAlertLabel.setBounds(258, 127, 89, 21);
		clientPanel.add(forGoldClientAlertLabel);
		forGoldClientAlertLabel.setVisible(isCurrentClientGold);
		forGoldClientAlertLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));

		goldClientIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + GOLD_CLIENT_ICON));
		goldClientIconLabel.setBounds(263, 82, 58, 41);
		clientPanel.add(goldClientIconLabel);

		newClientButton = new JButton("New Client");
		newClientButton.setBounds(10, 159, 112, 33);
		clientPanel.add(newClientButton);

		chargeCashButton = new JButton("Charge cash");
		chargeCashButton.setBounds(133, 159, 112, 33);
		clientPanel.add(chargeCashButton);

		forClientDiscountLabel = new JLabel(GoldClient.DISCOUNT_PERCENTAGE + "%");
		forClientDiscountLabel.setBounds(133, 131, 80, 14);
		clientPanel.add(forClientDiscountLabel);
		
		clientDiscountLabel = new JLabel("Discount of");
		clientDiscountLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		clientDiscountLabel.setBounds(10, 131, 89, 14);
		clientPanel.add(clientDiscountLabel);

		setCurrentClient((Client) clientsComboBox.getSelectedItem());

		// Sale information
		JPanel offerPanel = new JPanel();
		offerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		offerPanel.setBounds(393, 58, 418, 405);
		getContentPane().add(offerPanel);
		offerPanel.setLayout(null);

		addOfferButton = new JButton("Add product");
		addOfferButton.setIcon(getRenderer().renderImage(AppData.ImagePath.POS_ICON + ADD_OFFER_ICON));
		addOfferButton.setHorizontalTextPosition(AbstractButton.LEFT);
		addOfferButton.setBounds(144, 176, 196, 23);
		offerPanel.add(addOfferButton);

		deleteSelectedOfferButton = new JButton("Delete selected offer");
		deleteSelectedOfferButton.setIcon(getRenderer().renderImage(AppData.ImagePath.POS_ICON + REMOVE_OFFER_ICON));
		deleteSelectedOfferButton.setHorizontalTextPosition(AbstractButton.LEFT);
		deleteSelectedOfferButton.setBounds(144, 210, 196, 23);
		offerPanel.add(deleteSelectedOfferButton);

		quantityTextField = new TextFieldPlaceHolder("Quantity");
		quantityTextField.setBounds(144, 139, 58, 20);
		offerPanel.add(quantityTextField);

		JLabel forQuantityLabel = new JLabel("Quantity");
		forQuantityLabel.setBounds(49, 141, 77, 14);
		offerPanel.add(forQuantityLabel);
		forQuantityLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		serviceRadioButton = new JRadioButton("Service");
		serviceRadioButton.setBounds(49, 94, 77, 23);
		offerPanel.add(serviceRadioButton);
		serviceRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioButtonGroup.add(serviceRadioButton);

		productPrice = new JLabel("0.00" + Shop.COIN_BADGE);
		productPrice.setBounds(333, 58, 58, 14);
		offerPanel.add(productPrice);

		productsComboBox = new MyComboBox<Integer, Product>(Intent.getInstance().getProducts());
		productsComboBox.setBounds(144, 58, 165, 20);
		offerPanel.add(productsComboBox);

		productPrice.setText(StringHelper.formatAmount(((Product) productsComboBox.getSelectedItem()).getPrice()));

		servicePrice = new JLabel("0.00" + Shop.COIN_BADGE);
		servicePrice.setBounds(333, 99, 58, 14);
		offerPanel.add(servicePrice);

		servicesComboBox = new MyComboBox<Integer, Service>(Intent.getInstance().getServices());
		servicesComboBox.setBounds(144, 94, 165, 20);
		offerPanel.add(servicesComboBox);

		servicePrice.setText(StringHelper.formatAmount(((Service) servicesComboBox.getSelectedItem()).getPrice()));

		productRadioButton = new JRadioButton("Product");
		productRadioButton.setBounds(49, 58, 77, 23);
		offerPanel.add(productRadioButton);
		productRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		JLabel forProductsTableLabel = new JLabel("Sale lines");
		forProductsTableLabel.setBounds(10, 212, 105, 21);
		offerPanel.add(forProductsTableLabel);
		forProductsTableLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel saleLinesIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + SALE_LINES_ICON));
		saleLinesIconLabel.setBounds(75, 210, 35, 33);
		offerPanel.add(saleLinesIconLabel);

		JPanel saleTotalLabel = new JPanel();
		saleTotalLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		saleTotalLabel.setBounds(527, 469, 284, 100);
		getContentPane().add(saleTotalLabel);
		saleTotalLabel.setLayout(null);

		// Sale total information
		JLabel totalIconLabel = new JLabel(getRenderer().renderImage(AppData.ImagePath.POS_ICON + TOTAL_SALE_ICON));
		totalIconLabel.setBounds(23, 29, 67, 60);
		saleTotalLabel.add(totalIconLabel);

		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setBounds(100, 11, 75, 14);
		saleTotalLabel.add(forSubtotalLabel);
		forSubtotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

		subtotalLabel = new JLabel("0.00" + Shop.COIN_BADGE);
		subtotalLabel.setBounds(185, 13, 104, 14);
		saleTotalLabel.add(subtotalLabel);

		JLabel forTaxLabel = new JLabel("Tax (" + Shop.VAT_TAX_PERCENTAGE + "%)");
		forTaxLabel.setBounds(100, 36, 90, 14);
		saleTotalLabel.add(forTaxLabel);
		forTaxLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

		taxLabel = new JLabel("0.00" + Shop.COIN_BADGE);
		taxLabel.setBounds(184, 38, 107, 14);
		saleTotalLabel.add(taxLabel);

		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setBounds(100, 61, 58, 28);
		saleTotalLabel.add(forTotalLabel);
		forTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

		totalLabel = new JTextField("0.00" + Shop.COIN_BADGE);
		totalLabel.setBounds(184, 64, 90, 25);
		saleTotalLabel.add(totalLabel);
		totalLabel.setEditable(false);
		totalLabel.setFont(new Font("", Font.BOLD, 14));
	}

	@Override
	protected void loadListeners() {
		clientsComboBox.addActionListener((e) -> setCurrentClient((Client) clientsComboBox.getSelectedItem()));

		newClientButton.addActionListener((e) -> new NewClientView());
		chargeCashButton.addActionListener((e) -> new ChargeCashView(currentClient));

		logoutButton.addActionListener((e) -> logoutController.logout());
		exitButton.addActionListener(new OnExitAction(this));

		paymentButton.addActionListener((e) -> {
			PaymentController paymentController = new PaymentController(this, BillView.class.getName());
			try {
				paymentController.processPayment(saleController.getSale(), (Client) clientsComboBox.getSelectedItem(),
						isCurrentClientGold, loggedCommercial);
			} catch (EmptyPaymentException | CantAffordException err) {
				Alert.showError(this, err.getMessage());
			}
		});

		productsComboBox.addActionListener((e) -> productPrice
				.setText(StringHelper.formatAmount(((Product) productsComboBox.getSelectedItem()).getPrice())));
		servicesComboBox.addActionListener((e) -> servicePrice
				.setText(StringHelper.formatAmount(((Service) servicesComboBox.getSelectedItem()).getPrice())));

		serviceRadioButton.addActionListener((e) -> addOfferButton.setText("Add service"));
		productRadioButton.addActionListener((e) -> addOfferButton.setText("Add product"));

		addOfferButton.addActionListener((e) -> {
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
