package com.devdream.ui;

import java.awt.Font;
import java.awt.Label;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.devdream.controller.SaleController;
import com.devdream.data.AppData;
import com.devdream.data.bind.Intent;
import com.devdream.exception.PDFCancelledExpeption;
import com.devdream.model.Bill;
import com.devdream.ui.custom.Alert;
import com.devdream.ui.custom.OfferTable;
import com.devdream.util.StringHelper;

/**
 * This bill shows the result of the proceed payment.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class BillView extends View {

	private static final long serialVersionUID = -8859657268655367380L;
	
	//
	// Global
	private static final String COMMERCIAL_ICON = "commercial.png";
	private static final String CLIENT_ICON = "client.png";
	private static final String TOTAL_ICON = "total.png";
	private static final String JUST_GETS_GOLD_CLIENT_ICON = "justgetgoldclient.png";
	private static final String PDF_ICON = "pdf.png";
	private static final String PRINT_ICON = "print.png";
	private static final String NEW_SALE_ICON = "newsale.png";
	
	//
	// Attributes
	private SaleController saleController;
	private Bill bill;
	
	private OfferTable productsSaleLinesTable;
	private OfferTable servicesSaleLinesTable;
	private JButton generatePDFButton;
	private JButton printPrinterButton;
	private JButton newSaleButton;
	
	//
	// Constructors
	public BillView() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		saleController = new SaleController(this, POSView.class.getName());
		
		bill = Intent.getInstance().getCurrentBill();
		
		loadUI();
		
		loadListeners();
		
		render();
	}

	@Override
	protected void loadUI() {
		// Title
		JLabel billInfoLabel = new JLabel("2Wheels Bill information");
		billInfoLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		billInfoLabel.setBounds(275, 11, 281, 28);
		add(billInfoLabel);
		
		// Bill info
		JLabel forBillDateLabel = new JLabel("Bill date:");
		forBillDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forBillDateLabel.setBounds(29, 22, 56, 14);
		add(forBillDateLabel);
		
		JLabel billDateLabel = new JLabel(bill.getSale().getSaleDate());
		billDateLabel.setBounds(109, 23, 76, 14);
		add(billDateLabel);
		
		// Commercial info
		JLabel forCommercialLabel = new JLabel("Commercial");
		forCommercialLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forCommercialLabel.setBounds(29, 70, 103, 14);
		add(forCommercialLabel);

		JLabel commercialIconLabel = new JLabel(renderImage(AppData.ImagePath.POS_ICON + COMMERCIAL_ICON));
		commercialIconLabel.setBounds(119, 56, 56, 40);
		add(commercialIconLabel);

		JLabel forIdCommercialLabel = new JLabel("ID:");
		forIdCommercialLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forIdCommercialLabel.setBounds(32, 107, 46, 14);
		add(forIdCommercialLabel);
		
		JLabel commercialIdLabel = new JLabel(bill.getCommercial().ID);
		commercialIdLabel.setBounds(98, 108, 87, 14);
		add(commercialIdLabel);
		
		JLabel forCommercialNameLabel = new JLabel("Name:");
		forCommercialNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialNameLabel.setBounds(32, 132, 46, 14);
		add(forCommercialNameLabel);
		
		JLabel commercialLabel = new JLabel(bill.getCommercial().getName());
		commercialLabel.setBounds(99, 133, 68, 14);
		add(commercialLabel);

		JLabel forCommercialSurnameLabel = new JLabel("Surname");
		forCommercialSurnameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialSurnameLabel.setBounds(29, 157, 68, 14);
		add(forCommercialSurnameLabel);
		
		JLabel commercialSurnameLabel = new JLabel(bill.getCommercial().getSurname());
		commercialSurnameLabel.setBounds(98, 157, 122, 14);
		add(commercialSurnameLabel);
		
		// Client info
		Label forClientLabel = new Label("Client");
		forClientLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forClientLabel.setBounds(433, 68, 46, 14);
		add(forClientLabel);
		
		JLabel clientIconLabel = new JLabel(renderImage(AppData.ImagePath.POS_ICON + CLIENT_ICON));
		clientIconLabel.setBounds(485, 56, 56, 40);
		add(clientIconLabel);

		JLabel forIdLabel = new JLabel("ID");
		forIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forIdLabel.setBounds(433, 107, 46, 14);
		add(forIdLabel);
		JLabel idLabel = new JLabel(bill.getClient().ID);
		idLabel.setBounds(499, 108, 122, 14);
		add(idLabel);
		
		JLabel forClientNameLabel = new JLabel("Name");
		forClientNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientNameLabel.setBounds(433, 132, 46, 14);
		add(forClientNameLabel);

		JLabel clientNameLabel = new JLabel(bill.getClient().getName());
		clientNameLabel.setBounds(499, 133, 122, 14);
		add(clientNameLabel);

		JLabel forClientSurnameClient = new JLabel("Surname");
		forClientSurnameClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientSurnameClient.setBounds(433, 157, 68, 14);
		add(forClientSurnameClient);
		
		JLabel clientSurnameLabel = new JLabel(bill.getClient().getSurname());
		clientSurnameLabel.setBounds(499, 157, 122, 14);
		add(clientSurnameLabel);
		
		// Check if the client is a gold client, if it is not, check if the client has
		// just achieved the GoldClient, else show how much money takes to get it.
		JLabel remainingCashGoldClientLabel = new JLabel();
		if (!bill.getClient().isGoldClient()) {
			if (bill.getClient().getRemainingCashGoldClient() <= 0) {
				JLabel forRemainingCashGoldClientLabel = new JLabel("You are now a Gold Client!");
				forRemainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				forRemainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
				forRemainingCashGoldClientLabel.setBounds(652, 27, 210, 14);
				add(forRemainingCashGoldClientLabel);
				
				remainingCashGoldClientLabel.setIcon(renderImage(AppData.ImagePath.POS_ICON + JUST_GETS_GOLD_CLIENT_ICON));
				remainingCashGoldClientLabel.setBounds(671, 56, 139, 139);
			} else {
				JLabel forRemainingCashGoldClientLabel = new JLabel("Remainingfor cash (Gold Client)");
				forRemainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				forRemainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
				forRemainingCashGoldClientLabel.setBounds(433, 180, 220, 14);
				add(forRemainingCashGoldClientLabel);
				
				remainingCashGoldClientLabel.setText(StringHelper.formatAmount(bill.getClient().getRemainingCashGoldClient()));
				remainingCashGoldClientLabel.setBounds(648, 181, 79, 14);
			}
		}
		remainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
		add(remainingCashGoldClientLabel);
		
		// Sale offer sale lines
		JLabel forProductsLabel = new JLabel("Products");
		forProductsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forProductsLabel.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		forProductsLabel.setBounds(25, 219, 79, 14);
		add(forProductsLabel);
		
		JScrollPane forProductsTableScrollPane = new JScrollPane();
		forProductsTableScrollPane.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		forProductsTableScrollPane.setBounds(25, 244, 377, 101);
		add(forProductsTableScrollPane);
		
		productsSaleLinesTable = new OfferTable(bill.getSale().getProductsFromSaleLines());
		productsSaleLinesTable.update();
		forProductsTableScrollPane.setViewportView(productsSaleLinesTable);
		forProductsTableScrollPane.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		
		if (bill.getSale().getProductsFromSaleLines().isEmpty()) {
			JLabel noProductsOnSaleLabel = new JLabel("No products on the sale line.");
			noProductsOnSaleLabel.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));
			noProductsOnSaleLabel.setBounds(86, 262, 185, 14);
			add(noProductsOnSaleLabel);
		}
		
		JLabel forServicesLabel = new JLabel("Services");
		forServicesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forServicesLabel.setVisible(!bill.getSale().getServicesFromSaleLines().isEmpty());
		forServicesLabel.setBounds(433, 219, 79, 14);
		add(forServicesLabel);
		
		JScrollPane forServiceTableScrollPane = new JScrollPane();
		forServiceTableScrollPane.setVisible(!bill.getSale().getServicesFromSaleLines().isEmpty());
		forServiceTableScrollPane.setBounds(433, 244, 377, 101);
		add(forServiceTableScrollPane);
		
		servicesSaleLinesTable = new OfferTable(bill.getSale().getServicesFromSaleLines());
		servicesSaleLinesTable.update();
		forServiceTableScrollPane.setViewportView(servicesSaleLinesTable);

		if (bill.getSale().getServicesFromSaleLines().isEmpty()) {
			JLabel noServicesOnSaleLabel = new JLabel("No services on the sale line.");
			noServicesOnSaleLabel.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));
			noServicesOnSaleLabel.setBounds(429, 262, 185, 13);
			add(noServicesOnSaleLabel);
		}
		
		// Total info
		JLabel totalIconLabel = new JLabel(renderImage(AppData.ImagePath.POS_ICON + TOTAL_ICON));
		totalIconLabel.setBounds(276, 403, 67, 60);
		add(totalIconLabel);
		
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forSubtotalLabel.setBounds(370, 403, 57, 14);
		add(forSubtotalLabel);
		
		JLabel subtotalLabel = new JLabel(bill.getSale().getFormattedSubtotal());
		subtotalLabel.setBounds(437, 403, 101, 14);
		add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forTaxLabel.setBounds(370, 428, 46, 14);
		add(forTaxLabel);
		
		JLabel taxLabel = new JLabel(bill.getSale().getFormattedTax());
		taxLabel.setBounds(435, 428, 101, 14);
		add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		forTotalLabel.setBounds(369, 453, 58, 28);
		add(forTotalLabel);
		
		JTextField totalLabel = new JTextField(bill.getSale().getFormattedTotal());
		totalLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		totalLabel.setEditable(false);
		totalLabel.setBounds(433, 453, 103, 25);
		add(totalLabel);

		// New sale button
		newSaleButton = new JButton("New sale");
		newSaleButton.setIcon(renderImage(AppData.ImagePath.POS_ICON + NEW_SALE_ICON));
		newSaleButton.setHorizontalTextPosition(AbstractButton.LEFT);
		newSaleButton.setBounds(65, 416, 180, 48);
		add(newSaleButton);
		
		// Generation options
		generatePDFButton = new JButton("Generate PDF");
		generatePDFButton.setIcon(renderImage(AppData.ImagePath.POS_ICON + PDF_ICON));
		generatePDFButton.setHorizontalTextPosition(AbstractButton.LEFT);
		generatePDFButton.setBounds(581, 370, 180, 47);
		add(generatePDFButton);
		
		printPrinterButton = new JButton("Print (Printer)");
		printPrinterButton.setIcon(renderImage(AppData.ImagePath.POS_ICON + PRINT_ICON));
		printPrinterButton.setHorizontalTextPosition(AbstractButton.LEFT);
		printPrinterButton.setBounds(581, 450, 182, 48);
		add(printPrinterButton);
	}

	@Override
	protected void loadListeners() {
		generatePDFButton.addActionListener((e) -> {
			try {
				bill.generatePDF();
				
				Alert.showInfo(this, "Bill PDF generated and saved.");
			}catch(PDFCancelledExpeption err) {
				Alert.showError(this, err.getMessage());
			} catch (COSVisitorException | IOException err) {
				Alert.showError(this, "Error generating the PDF file.");
			}
		});
		
		printPrinterButton.addActionListener((e) -> {
			try {
				bill.print();
				
				Alert.showInfo(this, "Printed successfully.");
			} catch (NullPointerException err) {
				Alert.showError(this, "Printing cancelled.");
			}
		});
		
		newSaleButton.addActionListener((e) -> {
			saleController.newSale();
		});
	}
	
}
