package entities;

import java.util.ArrayList;
import java.util.HashMap;
import states.*;
import je.visual.Vector2D;
import kotlin.jvm.functions.Function0;
import java.util.Map.Entry;

/***
* Represents an object that, when interacted with, presents the user with options so they
* can choose their next course of action.
*/
public class ActionObject extends Entity {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
    *********************/
    
    // The question to ask the user so they know what they should be selecting..
    String actionQuestion = "";

    // The options to put on the action box as well as the actions to carry out.
    HashMap<String, Function0> options;
	
    // Whether or not this action object is solid (which it should be for all AO).
    boolean solid = true;
    



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
    *********************/
    
    public ActionObject(Vector2D pos, WorldState ws) {
        super(pos, ws);
        this.options = new HashMap<String, Function0>();
    }






    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    public HashMap<String, Function0> getOptions() { return options; }

    public String getActionQuestion() { return actionQuestion; }

    public boolean isSolid() { return solid; }




    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    public void setQuestion(String a) { this.actionQuestion = a; }


    public void cloneOptions(ActionObject from) {
        for (Entry<String,Function0> pair : from.getOptions().entrySet()){
            this.options.put(pair.getKey(), pair.getValue());
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
    }


}