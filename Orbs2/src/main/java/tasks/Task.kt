package tasks;

import states.*

public class Task(var description: String,
                  var id: String,
                  var dependsOnTask: Task? = null,
                  var completion: (()->Unit)? = null ) {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

    /** Whether or not the task has even been stared. */
    internal var started: Boolean


    /** Whether or not the task has been completed. */
    internal var completed: Boolean







    /********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

    init {
        this.started = false;
        this.completed = false;
    }
    



    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    /** Returns whether or not this task has been started. */
    public fun isStarted(): Boolean = this.started


    /** Returns whether or not this task has been completed. */
    public fun isCompleted(): Boolean = this.completed

    
    /** Returns the description of the task. */
    public fun getTaskDescription(): String = description


    /** Returns the ID of the task. */
    public fun getID(): String = id


    /** Returns a string description of this task. */
    override public fun toString(): String {
        return """Description: ${getTaskDescription()}
        Started: ${isStarted()}
        Completed: ${isCompleted()}
        Short Name: ${getID()}
        """;
    }





    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    /** Sets the task that this task requires to be completed before starting. */
    public fun setDependsOn(t: Task) {
        dependsOnTask = t;
    }


    /** Handles starting the first task. If this task depends on another one to be completed first
     * it will throw an exception.
    */
    public fun start(): Task {
        val dep = dependsOnTask

        // If there is no task to depend on...
        if(dep == null) {
            started = true;
        } else {
            // Otherwise check if it is completed.
            if(dep.isCompleted()) {
                this.started = true;
            } else {
                throw Exception("Another task must be completed before starting this one. Please complete: " 
                                    + dep.getTaskDescription());
            }
        }
        return this
    }

    
    /** Sets the task to completed. */
    public fun complete() {
        this.completed = true;
            
        val comp = completion
        if(comp != null) {
            comp()
        }
    }


    /** Directly sets the completed variable. Used for loading data. */
    public fun setCompleted() {
        this.completed = true;
    }


    public fun setStarted() {
        this.started = true;
    }


    /** Sets the completed variable to false. */
    public fun setNotStarted() {
        this.started = false;
    }

    /** Directly sets completed to false. */
    public fun setNotCompleted() {
        this.completed = false;
    }



    /** Sets the task to completed. Uses a completion block in case you want to define something
    here to do right after completing the task. 
        Use: 
        new Function0<Unit>() {
            public Unit invoke() {
                System.out.println("Completed task");
                return null;
            }
        }
    */
    public fun complete( comp: (()->Unit)? ) {
        this.completed = true;
        
        if(comp != null) {
            comp()
        }
    }


}
// End of class