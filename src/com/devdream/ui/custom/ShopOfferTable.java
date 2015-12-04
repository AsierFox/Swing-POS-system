package com.devdream.ui.custom;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.devdream.model.ShopOffer;

public class ShopOfferTable extends JTable {

	private static final long serialVersionUID = -5357968916571336456L;
	
	private int editableQtyColIndex;
	private DefaultTableModel model;
	
	public ShopOfferTable() {
		model = new DefaultTableModel();
		setModel(model);
		setOffersTableHeader();
		getTableHeader().setReorderingAllowed(false);
		// Last one is always the quantity
		editableQtyColIndex = model.getColumnCount() - 1;
		/* OFFER ROW SELECTED
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow = getSelectedRow(),
					selectedCol = getSelectedColumn();
				
				System.out.println("\nSelected Row: " + selectedRow +
						"\nSelected Colum: " + selectedCol);
				
				super.mouseClicked(e);
			}
		});
		*/
	}
	
	public void addOfferToTable(ShopOffer offer, int qty) {
		Vector<String> row = new Vector<String>();
		row.addElement(Integer.toString(model.getRowCount() + 1));
		row.addElement(Integer.toString(offer.getID()));
		row.addElement(offer.getName());
		row.addElement(Float.toString(offer.getPrice()));
		row.addElement(Integer.toString(qty));
		model.addRow(row);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == editableQtyColIndex;
	}
	
	private void setOffersTableHeader() {
		model.addColumn("N line");
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Unit price");
		model.addColumn("Qty.");
	}
	
}