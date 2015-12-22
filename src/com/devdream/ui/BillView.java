package com.devdream.ui;

import java.awt.Font;
import java.awt.Label;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.devdream.controller.SaleController;
import com.devdream.data.AppData;
import com.devdream.data.bind.Intent;
import com.devdream.helper.StringHelper;
import com.devdream.model.Bill;
import com.devdream.ui.custom.Alert;
import com.devdream.ui.custom.OfferTable;
import com.devdream.util.PDFBuilder;
import com.devdream.util.ViewRenderer;

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
	private static final String JUST_GETS_GOLD_CLIENT = "justgetgoldclient.png";
	
	//
	// Attributes
	private Bill bill;
	
	private OfferTable productsSaleLinesTable;
	private OfferTable servicesSaleLinesTable;
	private JButton generatePdfButton;
	private JButton printPrinterButton;
	private JButton newSaleButton;
	
	//
	// Constructors
	public BillView() {
		super();
		ViewRenderer renderer = new ViewRenderer(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		bill = Intent.getInstance().getCurrentBill();
		
		loadUI();
		
		loadListeners();
		
		renderer.render();
	}

	@Override
	protected void loadUI() {
		// Title
		JLabel billInfoLabel = new JLabel("2Wheels Bill Info");
		billInfoLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		billInfoLabel.setBounds(275, 11, 152, 14);
		getContentPane().add(billInfoLabel);
		
		// Bill info
		JLabel forBillDateLabel = new JLabel("Bill date:");
		forBillDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forBillDateLabel.setBounds(25, 44, 56, 14);
		getContentPane().add(forBillDateLabel);
		
		JLabel billDateLabel = new JLabel(bill.getSale().getSaleDate());
		billDateLabel.setBounds(91, 45, 76, 14);
		getContentPane().add(billDateLabel);

		// Offers info
		JLabel forProductsLabel = new JLabel("Products");
		forProductsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forProductsLabel.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		forProductsLabel.setBounds(25, 219, 79, 14);
		getContentPane().add(forProductsLabel);
		
		JScrollPane forProductsTableScrollPane = new JScrollPane();
		forProductsTableScrollPane.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		forProductsTableScrollPane.setBounds(25, 244, 377, 101);
		getContentPane().add(forProductsTableScrollPane);
		
		productsSaleLinesTable = new OfferTable(bill.getSale().getProductsFromSaleLines());
		productsSaleLinesTable.update();
		forProductsTableScrollPane.setViewportView(productsSaleLinesTable);
		forProductsTableScrollPane.setVisible(!bill.getSale().getProductsFromSaleLines().isEmpty());
		
		JLabel forServicesLabel = new JLabel("Services");
		forServicesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forServicesLabel.setVisible(!bill.getSale().getServicesFromSaleLines().isEmpty());
		forServicesLabel.setBounds(433, 219, 79, 14);
		getContentPane().add(forServicesLabel);
		
		JScrollPane forServiceTableScrollPane = new JScrollPane();
		forServiceTableScrollPane.setVisible(!bill.getSale().getServicesFromSaleLines().isEmpty());
		forServiceTableScrollPane.setBounds(433, 244, 377, 101);
		getContentPane().add(forServiceTableScrollPane);
		
		servicesSaleLinesTable = new OfferTable(bill.getSale().getServicesFromSaleLines());
		servicesSaleLinesTable.update();
		forServiceTableScrollPane.setViewportView(servicesSaleLinesTable);
		
		// Total info
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forSubtotalLabel.setBounds(370, 403, 57, 14);
		getContentPane().add(forSubtotalLabel);
		JLabel subtotalLabel = new JLabel(StringHelper.formatAmount((bill.getSale().getSubtotal())));
		subtotalLabel.setBounds(437, 403, 75, 14);
		getContentPane().add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forTaxLabel.setBounds(370, 428, 46, 14);
		getContentPane().add(forTaxLabel);
		JLabel taxLabel = new JLabel(StringHelper.formatAmount((bill.getSale().getTax())));
		taxLabel.setBounds(435, 428, 77, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forTotalLabel.setBounds(370, 453, 46, 14);
		getContentPane().add(forTotalLabel);
		JLabel totalLabel = new JLabel(StringHelper.formatAmount(bill.getSale().getTotal()));
		totalLabel.setBounds(435, 453, 77, 14);
		getContentPane().add(totalLabel);
		
		// Generation options
		generatePdfButton = new JButton("Generate PDF");
		generatePdfButton.setBounds(581, 370, 180, 47);
		getContentPane().add(generatePdfButton);
		
		printPrinterButton = new JButton("Print (Printer)");
		printPrinterButton.setBounds(581, 450, 182, 48);
		getContentPane().add(printPrinterButton);

		// New sale button
		newSaleButton = new JButton("New sale");
		newSaleButton.setBounds(65, 416, 180, 48);
		getContentPane().add(newSaleButton);
		
		// Commercial info
		JLabel forCommercialLabel = new JLabel("Commercial");
		forCommercialLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forCommercialLabel.setBounds(29, 82, 103, 14);
		getContentPane().add(forCommercialLabel);

		JLabel forCommercialNameLabel = new JLabel("Name:");
		forCommercialNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialNameLabel.setBounds(32, 145, 46, 14);
		getContentPane().add(forCommercialNameLabel);
		
		JLabel commercialLabel = new JLabel(bill.getCommercial().getName());
		commercialLabel.setBounds(88, 146, 68, 14);
		getContentPane().add(commercialLabel);
		
		// Client info
		Label forClientLabel = new Label("Client");
		forClientLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forClientLabel.setBounds(433, 68, 46, 14);
		getContentPane().add(forClientLabel);

		JLabel forIdLabel = new JLabel("ID:");
		forIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forIdLabel.setBounds(433, 95, 46, 14);
		getContentPane().add(forIdLabel);
		JLabel idLabel = new JLabel(bill.getClient().ID);
		idLabel.setBounds(499, 96, 87, 14);
		getContentPane().add(idLabel);
		
		JLabel forClientNameLabel = new JLabel("Name:");
		forClientNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientNameLabel.setBounds(433, 120, 46, 14);
		getContentPane().add(forClientNameLabel);

		JLabel clientNameLabel = new JLabel(bill.getClient().getName());
		clientNameLabel.setBounds(499, 121, 68, 14);
		getContentPane().add(clientNameLabel);
		
		JLabel clientIconLabel = new JLabel((Icon) null);
		clientIconLabel.setBounds(340, 53, 75, 40);
		getContentPane().add(clientIconLabel);
		
		JLabel remainingCashGoldClientLabel = new JLabel();
//		if (!bill.getClient().isGoldClient()) {
//			if (bill.getClient().getRemainingCashGoldClient() <= 0) {
				JLabel forRemainingCashGoldClientLabel = new JLabel("You are now a Gold Client!");
				forRemainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				forRemainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
				forRemainingCashGoldClientLabel.setBounds(652, 27, 210, 14);
				getContentPane().add(forRemainingCashGoldClientLabel);
				
				remainingCashGoldClientLabel.setIcon(getRenderer().renderImage(AppData.ImagePath.POS_ICON + JUST_GETS_GOLD_CLIENT));
				remainingCashGoldClientLabel.setBounds(671, 56, 139, 139);
//			} else {
//				JLabel forRemainingCashGoldClientLabel = new JLabel("Remainingfor cash Gold Client:");
//				forRemainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
//				forRemainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
//				forRemainingCashGoldClientLabel.setBounds(367, 142, 220, 14);
//				getContentPane().add(forRemainingCashGoldClientLabel);
//				
//				remainingCashGoldClientLabel.setText(StringHelper.formatAmount(bill.getClient().getRemainingCashGoldClient()));
//				remainingCashGoldClientLabel.setBounds(577, 142, 79, 14);
//			}
//		}
//		remainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
		getContentPane().add(remainingCashGoldClientLabel);
		
		
		JLabel commercialIdLabel = new JLabel(bill.getCommercial().ID);
		commercialIdLabel.setBounds(88, 121, 87, 14);
		getContentPane().add(commercialIdLabel);
		
		JLabel forIdCommercialLabel = new JLabel("ID:");
		forIdCommercialLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forIdCommercialLabel.setBounds(32, 120, 46, 14);
		getContentPane().add(forIdCommercialLabel);
		
		JLabel label = new JLabel((Icon) null);
		label.setBounds(276, 403, 67, 60);
		getContentPane().add(label);
		
		JLabel forClientSurnameClient = new JLabel("Surname");
		forClientSurnameClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientSurnameClient.setBounds(433, 145, 68, 14);
		getContentPane().add(forClientSurnameClient);
		
		JLabel clientSurnameLabel = new JLabel(bill.getClient().getSurname());
		clientSurnameLabel.setBounds(499, 145, 68, 14);
		getContentPane().add(clientSurnameLabel);
	}

	@Override
	protected void loadListeners() {
		generatePdfButton.addActionListener((e) -> {
			try {
				bill.generatePDF();
				
				Alert.showInfo(this, "Bill PDF generated. on\n" + PDFBuilder.FILE_PATH);
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
			SaleController saleController = new SaleController(this, POSView.class.getName());
			saleController.newSale();
		});
	}
	
}
