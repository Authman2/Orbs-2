package com.adeolauthman.Orbs2.views;

import javafx.scene.control.Button;
import javafx.scene.layout.Border;

public class PillButton extends Button {

	
	/** Constructs a new, pill shaped button object. */
	public PillButton(String text) {
		super(text);
		
		setBorder(Border.EMPTY);
		getStyleClass().add("PillButton");
		getStylesheets().add(getClass().getResource("../styles/PillButton.css").toExternalForm());
	}
	
	
	
}
