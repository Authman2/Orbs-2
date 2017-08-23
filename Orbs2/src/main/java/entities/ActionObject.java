package entities;

import java.util.ArrayList;
import java.util.HashMap;
import states.*;
import je.visual.Vector2D;
import kotlin.jvm.functions.Function1;
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
    ArrayList<String> options;
	
    // Whether or not this action object is solid (which it should be for all AO).
    boolean solid = true;

    // The function to carry out when an option is clicked.
    Function1 completion;
    



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
    *********************/
    
    public ActionObject(Vector2D pos, WorldState ws) {
        super(pos, ws);
        this.options = new ArrayList<String>();
    }






    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    public ArrayList<String> getOptions() { return options; }

    public Function1 getCompletion() { return completion; }

    public String getActionQuestion() { return actionQuestion; }

    public boolean isSolid() { return solid; }



    @Override
    public boolean nextTo(Entity ent) {
        return position.Distance(ent.position) <= 0.9;
    }




    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    public void setQuestion(String a) { this.actionQuestion = a; }

    public void setCompletion(Function1 c) { this.completion = c; }

    public void cloneOptions(ActionObject from) {
        this.options.addAll(from.getOptions());
    }




    

}