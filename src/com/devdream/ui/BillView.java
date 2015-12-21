package com.devdream.ui;

import java.awt.Font;
import java.awt.Label;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.devdream.controller.SaleController;
import com.devdream.data.AppData;
import com.devdream.data.bind.Intent;
import com.devdream.helper.StringHelper;
import com.devdream.model.Bill;
import com.devdream.model.SaleLine;
import com.devdream.ui.custom.Alert;
import com.devdream.ui.custom.MyList;

/**
 * This bill shows the result of the proceed payment.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class BillView extends JFrame {

	private static final long serialVersionUID = -8859657268655367380L;
	
	private static final String JUST_GETS_GOLD_CLIENT = "newclient.png";
	
	//
	// Constructors
	public BillView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		// Get the bill
		Bill bill = Intent.getInstance().getCurrentBill();
		
		// Split type of offers
		ArrayList<SaleLine> billSaleLines     = bill.getSale().getSaleLines(),
							servicesSaleLines = new ArrayList<SaleLine>(),
							productsSaleLines = new ArrayList<SaleLine>();
		
		Iterator<SaleLine> it = billSaleLines.iterator();
		while (it.hasNext()) {
			SaleLine currSaleLine = it.next();
			if (currSaleLine.getProduct().isProduct()) {
				productsSaleLines.add(currSaleLine);
			}
			else {
				servicesSaleLines.add(currSaleLine);
			}
		}
		
		// Bill info title
		JLabel billInfoLabel = new JLabel("2Wheels Bill Info");
		billInfoLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		billInfoLabel.setBounds(170, 11, 152, 14);
		getContentPane().add(billInfoLabel);
		
		// Bill info
		JLabel forBillDateLabel = new JLabel("Bill date:");
		forBillDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forBillDateLabel.setBounds(28, 40, 56, 14);
		getContentPane().add(forBillDateLabel);
		
		JLabel billDateLabel = new JLabel(bill.getSale().getSaleDate());
		billDateLabel.setBounds(95, 41, 76, 14);
		getContentPane().add(billDateLabel);

		// Offers info
		JLabel forProductsLabel = new JLabel("Products");
		forProductsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forProductsLabel.setVisible(!productsSaleLines.isEmpty());
		forProductsLabel.setBounds(42, 220, 79, 14);
		getContentPane().add(forProductsLabel);
		
		JScrollPane forProductListScrollPane = new JScrollPane();
		forProductListScrollPane.setVisible(!productsSaleLines.isEmpty());
		forProductListScrollPane.setBounds(42, 245, 139, 101);
		getContentPane().add(forProductListScrollPane);
		
		MyList<SaleLine> productsList = new MyList<SaleLine>(productsSaleLines);
		forProductListScrollPane.setVisible(!productsSaleLines.isEmpty());
		forProductListScrollPane.setViewportView(productsList);
		
		JLabel forServicesLabel = new JLabel("Services");
		forServicesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forServicesLabel.setVisible(!servicesSaleLines.isEmpty());
		forServicesLabel.setBounds(264, 246, 79, 14);
		getContentPane().add(forServicesLabel);
		
		JScrollPane forServiceListScrollPane = new JScrollPane();
		forServiceListScrollPane.setVisible(!servicesSaleLines.isEmpty());
		forServiceListScrollPane.setBounds(264, 272, 150, 101);
		getContentPane().add(forServiceListScrollPane);
		
		MyList<SaleLine> servicesList = new MyList<SaleLine>(servicesSaleLines);
		servicesList.setVisible(!servicesSaleLines.isEmpty());
		forServiceListScrollPane.setViewportView(servicesList);
		
		// Total info
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forSubtotalLabel.setBounds(237, 407, 57, 14);
		getContentPane().add(forSubtotalLabel);
		JLabel subtotalLabel = new JLabel(StringHelper.formatAmount((bill.getSale().getSubtotal())));
		subtotalLabel.setBounds(304, 407, 75, 14);
		getContentPane().add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forTaxLabel.setBounds(237, 432, 46, 14);
		getContentPane().add(forTaxLabel);
		JLabel taxLabel = new JLabel(StringHelper.formatAmount((bill.getSale().getTax())));
		taxLabel.setBounds(302, 432, 77, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forTotalLabel.setBounds(237, 457, 46, 14);
		getContentPane().add(forTotalLabel);
		JLabel totalLabel = new JLabel(StringHelper.formatAmount((Intent.getInstance().getCurrentBill().getSale().getTotal())));
		totalLabel.setBounds(302, 457, 77, 14);
		getContentPane().add(totalLabel);
		
		// Generation options
		JButton generatePdfButton = new JButton("Generate PDF");
		generatePdfButton.addActionListener((e) -> {
			try {
				bill.generatePDF();
				Alert.showInfo(this, "Bill PDF generated");
			} catch (Exception err) {
				Alert.showError(this, "Error generating the PDF file!");
			}
		});
		generatePdfButton.setBounds(433, 367, 129, 23);
		getContentPane().add(generatePdfButton);
		
		JButton printPrinterButton = new JButton("Print (Printer)");
		printPrinterButton.addActionListener((e) -> bill.print());
		printPrinterButton.setBounds(435, 401, 127, 23);
		getContentPane().add(printPrinterButton);

		// New sale button
		JButton newSaleButton = new JButton("New sale");
		newSaleButton.addActionListener((e) -> {
			SaleController saleController = new SaleController(this, POSView.class.getName());
			saleController.newSale();
		});
		newSaleButton.setBounds(433, 448, 129, 23);
		getContentPane().add(newSaleButton);
		
		// Commercial info
		JLabel forCommercialLabel = new JLabel("Commercial");
		forCommercialLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forCommercialLabel.setBounds(42, 66, 103, 14);
		getContentPane().add(forCommercialLabel);

		JLabel forCommercialNameLabel = new JLabel("Name:");
		forCommercialNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forCommercialNameLabel.setBounds(42, 90, 46, 14);
		getContentPane().add(forCommercialNameLabel);
		
		JLabel commercialLabel = new JLabel(bill.getCommercial().getName());
		commercialLabel.setBounds(116, 90, 68, 14);
		getContentPane().add(commercialLabel);
		
		// Client info
		Label forClientLabel = new Label("Client");
		forClientLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		forClientLabel.setBounds(264, 66, 46, 14);
		getContentPane().add(forClientLabel);

		JLabel forIdLabel = new JLabel("ID:");
		forIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forIdLabel.setBounds(264, 106, 46, 14);
		getContentPane().add(forIdLabel);
		JLabel idLabel = new JLabel(bill.getClient().ID);
		idLabel.setBounds(340, 108, 87, 14);
		getContentPane().add(idLabel);
		
		JLabel forClientNameLabel = new JLabel("Name:");
		forClientNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forClientNameLabel.setBounds(264, 129, 46, 14);
		getContentPane().add(forClientNameLabel);

		JLabel clientNameLabel = new JLabel(bill.getClient().getName());
		clientNameLabel.setBounds(338, 130, 68, 14);
		getContentPane().add(clientNameLabel);
		
		JLabel clientIconLabel = new JLabel((Icon) null);
		clientIconLabel.setBounds(340, 53, 75, 40);
		getContentPane().add(clientIconLabel);
		
		JLabel forRemainingCashGoldClientLabel = new JLabel("Remainingfor cash Gold Client:");
		forRemainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		forRemainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
		forRemainingCashGoldClientLabel.setBounds(264, 157, 220, 14);
		getContentPane().add(forRemainingCashGoldClientLabel);
		
		JLabel remainingCashGoldClientLabel = new JLabel();
		if (!bill.getClient().isGoldClient()) {
			if ((int) bill.getClient().getRemainingCashGoldClient() <= 0) {
				remainingCashGoldClientLabel.setIcon(renderer.renderImage(AppData.ImagePath.NEW_CLIENT_ICON + JUST_GETS_GOLD_CLIENT));
			} else {
				StringHelper.formatAmount(bill.getClient().getRemainingCashGoldClient());
			}
		}
		remainingCashGoldClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		remainingCashGoldClientLabel.setVisible(!bill.getClient().isGoldClient());
		remainingCashGoldClientLabel.setBounds(264, 182, 243, 40);
		getContentPane().add(remainingCashGoldClientLabel);
		
		renderer.render();
	}

}
