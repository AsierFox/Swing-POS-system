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
public class OfferSaleLinesTable extends JTable {

	private static final long serialVersionUID = -5357968916571336456L;
	
	//
	// Attributes
	private DefaultTableModel model;
	private ArrayList<SaleLine> saleLines;
	
	//
	// Constructors
	public OfferSaleLinesTable(ArrayList<SaleLine> saleLines) {
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
		model.addColumn("line #");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Unit price");
		model.addColumn("Qty.");
	}
	
	/** Updates the JTable with the updates collection */
	public void update() {
		model.setRowCount(0);
		for (SaleLine saleLine : saleLines) {
			Vector<String> row = new Vector<String>();
			row.addElement(Integer.toString(model.getRowCount() + 1));
			row.addElement(Integer.toString(saleLine.getOffer().ID));
			row.addElement(saleLine.getOffer().getName());
			row.addElement(saleLine.getOffer().getFormattedPrice());
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
