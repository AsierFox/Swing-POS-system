package com.devdream.ui.custom;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.devdream.model.SaleLine;

/**
 * This class manages the JTable with offer sale lines.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class OfferTable extends JTable {

	private static final long serialVersionUID = -5357968916571336456L;
	
	//
	// Attributes
	private DefaultTableModel model;
	private ArrayList<SaleLine> saleLines;
	
	//
	// Constructors
	public OfferTable(ArrayList<SaleLine> saleLines) {
		this.saleLines = saleLines;
		model = new DefaultTableModel();
		setModel(model);
		setOffersTableHeader();
	}
	
	//
	// Methods
	/** Sets the header to the table */
	private void setOffersTableHeader() {
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Description");
		model.addColumn("Unit price");
		model.addColumn("Qty.");
	}
	
	public void update() {
		for (SaleLine saleLine : saleLines) {
			Vector<String> row = new Vector<String>();
			row.addElement(Integer.toString(saleLine.getProduct().ID));
			row.addElement(saleLine.getProduct().getName());
			row.addElement(saleLine.getProduct().getDescription());
			row.addElement(saleLine.getProduct().getFormattedPrice());
			row.addElement(Integer.toString(saleLine.getQuantity()));
			model.addRow(row);
		}
	}
	
	/** Sets the cells no editable */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
