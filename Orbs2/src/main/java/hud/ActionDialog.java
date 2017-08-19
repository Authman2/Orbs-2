package hud;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import kotlin.Pair;
import je.visual.Vector2D;
import kotlin.jvm.functions.Function0;
import main_package.Orbs2;
import states.*;
import javafx.scene.input.KeyCode;
import entities.*;

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
            // If the action box is not already open.
            if(actionDialogOpen == false) {
                worldState.getCurrentWorld().getActionableManager().getActionObjects().stream().forEach(ao -> {
                    if( ao.nextTo(worldState.getPlayer()) ) {
                        choices.clear();
                        clear();
                        add(ao.getActionQuestion());
                        
                        // Add all of the options and their function values.
                        String[] c = new String[ao.getOptions().size()];
                        int i = 0;
                        for(String s : ao.getOptions().keySet()) {
                            c[i] = s;
                            i++;
                        }
                        i = 0;
                        for(Function0 f : ao.getOptions().values()) {
                            if(i == 0) option1 = f;
                            else if(i == 1) option2 = f;
                            else if(i == 2) option3 = f;
                            i++;
                        }
                        i = 0;
                        setChoices(c);
                        toggle();
                        return;
                    }
                });

            // If it is open.
            } else {
                toggle();
                if(selectedIndex == 0) { if(option1 != null) option1.invoke(); }
                else if(selectedIndex == 1) { if(option2 != null) option2.invoke(); }
                else if(selectedIndex == 2) { if(option3 != null) option3.invoke(); }
                selectedIndex = 0;
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