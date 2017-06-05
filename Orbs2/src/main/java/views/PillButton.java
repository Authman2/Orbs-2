package views;

import javafx.scene.control.Button;
import javafx.scene.layout.Border;

import java.net.URL;

public class PillButton extends Button {

	
	/** Constructs a new, pill shaped button object. */
	public PillButton(String text) {
		super(text);
		
		setBorder(Border.EMPTY);
		getStyleClass().add("PillButton");
		URL path = getClass().getResource("/resources/styles/PillButton.css");
		getStylesheets().add(path.toString());
	}
	
} // End of class.