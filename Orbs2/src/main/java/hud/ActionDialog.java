package hud;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import kotlin.Pair;
import je.visual.Vector2D;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import main_package.Orbs2;
import states.*;
import javafx.scene.input.KeyCode;
import entities.*;
import java.util.StringTokenizer;

public class ActionDialog {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
    *********************/
    
    // A reference to the world state.
	WorldState worldState;

    // The question that is displayed on the dialog.
    String question;

    // All of the clickable choices to display.
    ArrayList<String> choices;
    
    // The function to carry out when an option is clicked.
    Function1 clickedFunction;

    // The position to start drawing the choices box.
    Vector2D choicesPosition, position;
    double[] choicesY;

    // The index of the currently selected item.
    int selectedIndex = 0;

    // Static boolean for when an action box, specifically, is open.
    public static boolean open = false;




    /********************
	*					*
	*	INITIALIZATION  *
	*					*
	*********************/

    public ActionDialog(WorldState ws) {
        worldState = ws;
        question = "";
        this.choices = new ArrayList<String>();
        this.position = new Vector2D(0, Orbs2.HEIGHT - 120);
        this.choicesPosition = new Vector2D(Orbs2.WIDTH - 130, Orbs2.HEIGHT - 250);
    }




    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    public boolean isOpen() { return open; }


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








    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    public void setChoices(String... c) {
        for(String p : c) { choices.add(p); }
        this.choicesY = new double[choices.size()];
        for(int i = 0; i < choicesY.length; i++) {
            if(i == 0) {
                choicesY[i] = choicesPosition.Y + 32;
            } else {
                choicesY[i] = choicesY[i-1] + 32;
            }
        }
    }
    
    public void toggle() {
        open = !open;
    }

    public void setClickedFunction(Function1 o) { this.clickedFunction = o; }



	/** Automatically opens the text box. */
	public void open() {
		open = true;
	}






    /********************
	*					*
	*	    OTHER		*
	*					*
    *********************/

    public void keyActions(KeyCode e) {
        if(e == KeyCode.UP || e == KeyCode.W) {
            selectedIndex--;
            if(selectedIndex < 0) selectedIndex = choices.size() - 1;
        }
        if(e == KeyCode.DOWN || e == KeyCode.S) {
            selectedIndex++;
            if(selectedIndex >= choices.size()) selectedIndex = 0;
        }
        if(e == KeyCode.C) {
            if(open == false) {
                selectedIndex = 0;
                for(ActionObject ao : worldState.getCurrentWorld().getActionableManager().getActionObjects()) {
                    // If an action object is next to the player, reset the choices.
                    if(ao.nextTo(worldState.getPlayer())) {
                        choices.clear();
                        this.question = ao.getActionQuestion();
                        this.clickedFunction = ao.getCompletion();

                        String[] c = new String[ao.getOptions().size()];
                        for(int i = 0; i < ao.getOptions().size(); i++) { 
                            c[i] = ao.getOptions().get(i);
                        }
                        setChoices(c);
                        toggle();
                        break;
                    }
                }
            }
            else {
                               
                if(clickedFunction != null) clickedFunction.invoke(choices.get(selectedIndex));
                this.choices.clear();
                this.question = "";
                open = false; 
            }
        }
    }






    /********************
	*					*
	*	   ABSTRACT		*
	*					*
    *********************/
    
    public void initialize() {
        selectedIndex = 0;
    }


    public void update() {
        
    }


    public void draw() {

        // TEXT BOX
        worldState.getGraphics().setStroke(Color.BLACK);
		worldState.getGraphics().setFill(Color.WHITE);
		worldState.getGraphics().fillRect(position.X, position.Y, Orbs2.WIDTH, 120);

		// Display the word wrapped version of the text.
        worldState.getGraphics().strokeText( wordWrapped(question), position.X + 5, position.Y + 15);
        



        // ACTION BOX
        // Draw the background of the choices
        worldState.getGraphics().setFill(Color.WHITE);
        worldState.getGraphics().fillRect((double)choicesPosition.X, (double)choicesPosition.Y, 125.0, 125.0);


        // Draw all of the choices and a background for each one.
        for(int i = 0; i < choices.size(); i++) {
            worldState.getGraphics().setStroke(Color.BLACK);
            worldState.getGraphics().strokeText(choices.get(i), (double)choicesPosition.X + 15, choicesY[i]);
        }

        // Draw the indicator on the selected option.
        worldState.getGraphics().setFill(Color.BLACK);
        if(selectedIndex <= choicesY.length - 1)
            worldState.getGraphics().fillRect((double)choicesPosition.X + 5, choicesY[selectedIndex] - 10, 5, 5);
    }

    

}