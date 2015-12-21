package com.devdream.ui.custom;

import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Personalized ComboBox for my custom objects.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class MyComboBox <I, E> extends JComboBox<E> {

	private static final long serialVersionUID = -1001219177283780102L;
	
	//
	// Attributes
	private DefaultComboBoxModel<E> model;
	private HashMap<I, E> items;
	
	//
	// Constructors
    public MyComboBox(HashMap<I, E> items) {
    	this.items = items;
    	setFont(new Font("SansSerif", Font.PLAIN, 12));
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
        Iterator<E> it = items.values().iterator();
        while (it.hasNext()) {
        	model.addElement(it.next());
        }
    }

}
