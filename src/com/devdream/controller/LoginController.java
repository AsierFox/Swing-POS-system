package com.devdream.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.devdream.data.SessionData;
import com.devdream.data.bind.Intent;
import com.devdream.model.Commercial;

public class LoginController extends Controller {
	
	private int selectedCommercial;
	private ArrayList<Commercial> commercials;

	public LoginController(JFrame actualView, String newWindowName, ArrayList<Commercial> commercials, JComboBox<String> comboBox) {
		super(actualView, newWindowName);
		this.commercials = commercials;
		comboBox.addActionListener((ActionEvent e) -> selectedCommercial = comboBox.getSelectedIndex());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Intent.getInstance().setSessionData(new SessionData(commercials.get(selectedCommercial)));
		super.getViewManager().changeView();
	}

}