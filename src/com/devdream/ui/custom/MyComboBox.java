package com.devdream.ui.custom;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Personalized ComboBox for my custom objects.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class MyComboBox <E> extends JComboBox<E> {

	private static final long serialVersionUID = -1001219177283780102L;
	
	//
	// Attributes
	private DefaultComboBoxModel<E> model;
	private ArrayList<E> items;
	
	//
	// Constructors
    public MyComboBox(ArrayList<E> items) {
    	this.items = items;
    	update();
    }
    
    //
    // Methods
    public E getSelected() {
		return items.get(getSelectedIndex());
    }
    
    public void update() {
        model = new DefaultComboBoxModel<E>();
        setModel(model);
        for (E u : items) {
        	model.addElement(u);
        }
    }

}
