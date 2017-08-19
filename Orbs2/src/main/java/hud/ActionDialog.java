package hud;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import kotlin.Pair;
import je.visual.Vector2D;
import kotlin.jvm.functions.Function0;
import main_package.Orbs2;
import states.*;
import javafx.scene.input.KeyCode;

public class ActionDialog extends TextBox {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
    *********************/
    
    // All of the clickable choices to display.
    ArrayList<String> choices;
    
    // The functions to carry out for each choice.
    Function0 option1, option2, option3;

    // The position to start drawing the choices box.
    Vector2D choicesPosition;
    double[] choicesY;

    // The index of the currently selected item.
    int selectedIndex = 0;

    // Static boolean for when an action box, specifically, is open.
    public static boolean actionDialogOpen = false;




    /********************
	*					*
	*	INITIALIZATION  *
	*					*
	*********************/

    public ActionDialog(WorldState ws) {
        super(ws);
        this.choices = new ArrayList<String>();
        this.choicesPosition = new Vector2D(Orbs2.WIDTH - 130, Orbs2.HEIGHT - 250);
    }




    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    @Override
    public boolean isOpen() {
        return actionDialogOpen;
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

    
    @Override
    public void toggle() {
        actionDialogOpen = !actionDialogOpen;
    }


    public void setOptionOne(Function0 o) { this.option1 = o; }
    public void setOptionTwo(Function0 o) { this.option2 = o; }
    public void setOptionThree(Function0 o) { this.option3 = o; }



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
            if(selectedIndex == 0) { if(option1 != null) option1.invoke(); }
            else if(selectedIndex == 1) { if(option2 != null) option2.invoke(); }
            else if(selectedIndex == 2) { if(option3 != null) option3.invoke(); }
        }
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
        super.draw();

        // Draw the background of the choices
        worldState.getGraphics().setFill(Color.WHITE);
        worldState.getGraphics().fillRect((double)choicesPosition.X, (double)choicesPosition.Y, 125.0, 125.0);


        // Draw all of the choices and a background for each one.
        for(int i = 0; i < choices.size(); i++) {
            worldState.getGraphics().setStroke(Color.BLACK);
            worldState.getGraphics().strokeText(choices.get(i), (double)choicesPosition.X + 15, choicesY[i]);
        }

        // Draw the indicator of the
        worldState.getGraphics().setFill(Color.BLACK);
        worldState.getGraphics().fillRect((double)choicesPosition.X + 5, choicesY[selectedIndex] - 10, 5, 5);
    }


}