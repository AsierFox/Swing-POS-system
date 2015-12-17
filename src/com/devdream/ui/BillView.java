package com.devdream.ui;

import java.awt.Label;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.devdream.controller.SaleController;
import com.devdream.data.bind.Intent;
import com.devdream.helper.StringHelper;
import com.devdream.model.Bill;
import com.devdream.model.SaleLine;
import com.devdream.ui.custom.InformationAlert;
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
	
	//
	// Constructors
	public BillView() {
		ViewRenderer renderer = new ViewRenderer(this);
		renderer.setCloseApplication();
		getContentPane().setLayout(null);
		
		Bill bill = Intent.getInstance().getCurrentBill();
		
		ArrayList<SaleLine> billSaleLines     = bill.getSale().getSaleLines(),
							servicesSaleLines = new ArrayList<SaleLine>(),
							productsSaleLines = new ArrayList<SaleLine>();
		
		Iterator<SaleLine> it = billSaleLines.iterator();
		while (it.hasNext()) {
			SaleLine currSaleLine = it.next();
			if (currSaleLine.getProduct().isProduct()) {
				productsSaleLines.add(currSaleLine);
			} else {
				servicesSaleLines.add(currSaleLine);
			}
		}
		
		JLabel lblBillInfo = new JLabel("2Wheels Bill Info");
		lblBillInfo.setBounds(170, 11, 102, 14);
		getContentPane().add(lblBillInfo);
		
		JLabel forBillDateLabel = new JLabel("Bill date:");
		forBillDateLabel.setBounds(33, 23, 56, 14);
		getContentPane().add(forBillDateLabel);
		
		JLabel billDateLabel = new JLabel(bill.getSale().getSaleDate());
		billDateLabel.setBounds(84, 23, 76, 14);
		getContentPane().add(billDateLabel);

		if (!productsSaleLines.isEmpty()) {
			JLabel forProductsLabel = new JLabel("Products");
			forProductsLabel.setBounds(10, 105, 79, 14);
			getContentPane().add(forProductsLabel);
			
			JScrollPane forProductListScrollPane = new JScrollPane();
			forProductListScrollPane.setBounds(10, 130, 139, 101);
			getContentPane().add(forProductListScrollPane);
			
			MyList<SaleLine> productsList = new MyList<SaleLine>(productsSaleLines);
			forProductListScrollPane.setViewportView(productsList);
		}
		
		if (!servicesSaleLines.isEmpty()) {
			JLabel forServicesLabel = new JLabel("Services");
			forServicesLabel.setBounds(170, 105, 79, 14);
			getContentPane().add(forServicesLabel);
			
			JScrollPane forServiceListScrollPane = new JScrollPane();
			forServiceListScrollPane.setBounds(170, 130, 150, 101);
			getContentPane().add(forServiceListScrollPane);
			
			MyList<SaleLine> servicesList = new MyList<SaleLine>(servicesSaleLines);
			forServiceListScrollPane.setViewportView(servicesList);
		}
		
		JLabel forSubtotalLabel = new JLabel("Subtotal");
		forSubtotalLabel.setBounds(209, 260, 57, 14);
		getContentPane().add(forSubtotalLabel);
		JLabel subtotalLabel = new JLabel(StringHelper.formatAmount((Intent.getInstance().getCurrentBill().getSale().getSubtotal())));
		subtotalLabel.setBounds(276, 260, 46, 14);
		getContentPane().add(subtotalLabel);
		
		JLabel forTaxLabel = new JLabel("Tax");
		forTaxLabel.setBounds(209, 285, 46, 14);
		getContentPane().add(forTaxLabel);
		JLabel taxLabel = new JLabel(StringHelper.formatAmount((Intent.getInstance().getCurrentBill().getSale().getTax())));
		taxLabel.setBounds(274, 285, 46, 14);
		getContentPane().add(taxLabel);
		
		JLabel forTotalLabel = new JLabel("Total");
		forTotalLabel.setBounds(209, 310, 46, 14);
		getContentPane().add(forTotalLabel);
		JLabel totalLabel = new JLabel(StringHelper.formatAmount((Intent.getInstance().getCurrentBill().getSale().getTotal())));
		totalLabel.setBounds(274, 310, 77, 14);
		getContentPane().add(totalLabel);
		
		JButton generatePdfButton = new JButton("Generate PDF");
		generatePdfButton.addActionListener((e) -> {
			try {
				bill.generatePDF();
				InformationAlert.show(this, "Bill PDF generated");
			} catch (Exception err) {
				err.printStackTrace();
			}
		});
		generatePdfButton.setBounds(359, 260, 129, 23);
		getContentPane().add(generatePdfButton);
		
		JButton printPrinterButton = new JButton("Print (Printer)");
		printPrinterButton.setBounds(361, 294, 127, 23);
		getContentPane().add(printPrinterButton);

		
		JButton newSaleButton = new JButton("New sale");
		newSaleButton.addActionListener((e) -> {
			SaleController saleController = new SaleController(this, POSView.class.getName());
			saleController.newSale();
		});
		newSaleButton.setBounds(359, 341, 129, 23);
		getContentPane().add(newSaleButton);
		

		JLabel forCommercialLabel = new JLabel("Commercial");
		forCommercialLabel.setBounds(30, 48, 74, 14);
		getContentPane().add(forCommercialLabel);

		JLabel forCommercialNameLabel = new JLabel("Name:");
		forCommercialNameLabel.setBounds(30, 72, 46, 14);
		getContentPane().add(forCommercialNameLabel);
		
		JLabel commercialLabel = new JLabel(bill.getCommercial().getName());
		commercialLabel.setBounds(104, 72, 68, 14);
		getContentPane().add(commercialLabel);
		
		Label forClientLabel = new Label("Client");
		forClientLabel.setBounds(225, 31, 46, 14);
		getContentPane().add(forClientLabel);

		JLabel forIdLabel = new JLabel("ID:");
		forIdLabel.setBounds(277, 31, 46, 14);
		getContentPane().add(forIdLabel);
		JLabel idLabel = new JLabel(bill.getClient().ID);
		idLabel.setBounds(333, 31, 46, 14);
		getContentPane().add(idLabel);
		
		JLabel forClientNameLabel = new JLabel("Name:");
		forClientNameLabel.setBounds(225, 56, 46, 14);
		getContentPane().add(forClientNameLabel);

		JLabel clientNameLabel = new JLabel(bill.getClient().getName());
		clientNameLabel.setBounds(283, 56, 68, 14);
		getContentPane().add(clientNameLabel);
		
		if (!bill.getClient().isGoldClient()) {
			JLabel forRemainingCashGoldClientLabel = new JLabel("Remainingfor cash Gold Client:");
			forRemainingCashGoldClientLabel.setBounds(225, 81, 166, 14);
			getContentPane().add(forRemainingCashGoldClientLabel);
			
			JLabel remainingCashGoldClientLabel = new JLabel(StringHelper.formatAmount(bill.getClient().getRemainingCashGoldClient()));
			remainingCashGoldClientLabel.setBounds(401, 81, 46, 14);
			getContentPane().add(remainingCashGoldClientLabel);
		}
		
		renderer.render();
	}

}
