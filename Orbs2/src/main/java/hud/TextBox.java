package hud;

import states.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import main_package.*;

public class TextBox {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// A reference to the world state.
	WorldState worldState;

	// The text slides to display.
	ArrayList<String> text;

	// The index of the current text slide.
	int currentSlide;

	// Whether or not the text box is open.
	public static boolean open;





	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public TextBox(WorldState ws) {
		worldState = ws;
		text = new ArrayList<String>();
	}
	



	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Goes to the next text slide. */
	public void next() {
		if(currentSlide >= text.size() - 1) {
			open = false;
			currentSlide = 0;
		} else {
			currentSlide++;
		}
	}



	/** Adds text to the text box. */
	public void add(String t) {
		text.add(t);
	}
	


	/** Opens/Closes the text box. */
	public void toggle() {
		open = !open;
	}



	/** Removes all the text slides. */
	public void clear() {
		text.clear();
		currentSlide = 0;
	}





	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	/** Whether or not this text box is open. */
	public boolean isOpen() {
		return open;
	}



	/** Returns the list of text slides. */
	public ArrayList<String> getTextSlides() {
		return text;
	}



	/** Returns whether or not this text box is on the last slide.*/
	public boolean onLast() {
		if(currentSlide == text.size() - 1) return true;
		return false;
	}
	
	



	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {

	}

	public void update() {

	}

	public void draw() {
		// worldState.getGraphics().setStroke(Color.BLACK);
		// worldState.getGraphics().drawRect(0, Orbs2.HEIGHT - 100, Orbs2.WIDTH, 100);
	}


}