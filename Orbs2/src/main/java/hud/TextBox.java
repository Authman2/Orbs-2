package hud;

import states.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import main_package.*;
import javafx.scene.control.TextArea;
import java.util.StringTokenizer;
import je.visual.Vector2D;
import java.util.function.Function;
import kotlin.jvm.functions.Function0;
import kotlin.Unit;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;

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

	// The position of the text box.
	Vector2D position;

	// Whether or not the text box is open.
	public static boolean open;

	// The text box that is currently opened.
	public static TextBox currentlyOpened;

	// The function to carry out once this textbox is closed.
	public static Function0<Unit> finishedFunction;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public TextBox(WorldState ws) {
		worldState = ws;
		text = new ArrayList<String>();
		position = new Vector2D(0, Orbs2.HEIGHT - 120);
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
			if(finishedFunction != null)
				finishedFunction.invoke();
				//finishedFunction.apply(null);
		} else {
			currentSlide++;
		}
	}



	/** Adds text to the text box. */
	public void add(String t) {
		text.add(t);
	}



	/** Sets the text slides from an array of string. */
	public void set(ArrayList<String> t) {
		text.clear();
		text.addAll(t);
	}
	


	/** Opens/Closes the text box. */
	public void toggle() {
		open = !open;
		if(open == true)
			currentlyOpened = this;
		else
			currentlyOpened = null;
	}


	/** Automatically opens the text box. */
	public void open() {
		open = true;
		currentlyOpened = this;
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
		worldState.getGraphics().setStroke(Color.BLACK);
		worldState.getGraphics().setFill(Color.WHITE);
		worldState.getGraphics().fillRect(position.X, position.Y, Orbs2.WIDTH, 120);

		// Display the word wrapped version of the text.
		worldState.getGraphics().strokeText( wordWrapped(text.get(currentSlide)), position.X + 5, position.Y + 15);
	}





	/********************
	*					*
	*	    OTHER		*
	*					*
	*********************/

	public void keyActions(KeyCode w) {
		if(w == KeyCode.C) {

			// If a text box is not already open.
			if(open == false) {
				worldState.getCurrentWorld().getNPCManager().getNPCS().stream().forEach(npc -> {
					// If you are next to an NPC.
					if(npc.nextTo(worldState.getPlayer())) {
						// Set the finished function.
						finishedFunction = npc.getFinishedFunction();

						// Set the menu options.
						worldState.getNPCMenu().setMenuItem(0, new Pair<String, Function<?,?>>("Interact", e -> { 
							if(npc.getSpeech().size() > 0) {
								set(npc.getSpeech());
								toggle();
								worldState.getNPCMenu().toggle();
							}
							else {
								worldState.getNPCMenu().toggle();
							}
							return null;
						}));
						worldState.getNPCMenu().toggle();
					}
				});
			} 
			// If a text box is open.
			else {
				if(TextBox.currentlyOpened != null) {
					TextBox.currentlyOpened.next();
				}
			}
		}
	}




	// The width that a line can be, determined by the width of the screen.
	int lineWidth = 100;

	/** Returns a word wrapped version of the text. */
	private String wordWrapped(String text) {
		String ret = "";
		int spaceLeft = lineWidth;
		int spaceWidth = 1;

		StringTokenizer tokenizer = new StringTokenizer(text);
		while(tokenizer.hasMoreTokens()) {
			String wordInLine = tokenizer.nextToken();
			
			// Add a line break.
			if((wordInLine.length() + spaceWidth) > spaceLeft) {
				ret += "\n" + wordInLine + " ";
				spaceLeft = lineWidth - wordInLine.length();
			} else {
				ret += wordInLine + " ";
				spaceLeft -= (wordInLine.length() + spaceWidth);
			}
		}
		return ret;
	}

}



















