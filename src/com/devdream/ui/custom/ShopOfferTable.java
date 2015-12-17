package com.devdream.ui.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.devdream.model.SaleLine;

/**
 * This class manages the JTable with offers objects instances.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class ShopOfferTable extends JTable {

	private static final long serialVersionUID = -5357968916571336456L;
	
	//
	// Attributes
	private DefaultTableModel model;
	private ArrayList<SaleLine> saleLines;
	
	//
	// Constructors
	public ShopOfferTable(ArrayList<SaleLine> saleLines) {
		this.saleLines = saleLines;
		model = new DefaultTableModel();
		setModel(model);
		setOffersTableHeader();
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
	}
	
	//
	// Methods
	private void setOffersTableHeader() {
		model.addColumn("line #");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Unit price");
		model.addColumn("Qty.");
	}
	
	public void update() {
		model.setRowCount(0);
		Iterator<SaleLine> it = saleLines.iterator();
		while (it.hasNext()) {
			SaleLine saleLine = it.next();
			Vector<String> row = new Vector<String>();
			row.addElement(Integer.toString(model.getRowCount() + 1));
			row.addElement(Integer.toString(saleLine.getProduct().getID()));
			row.addElement(saleLine.getProduct().getName());
			row.addElement(Float.toString(saleLine.getProduct().getPrice()));
			row.addElement(Integer.toString(saleLine.getQuantity()));
			model.addRow(row);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
