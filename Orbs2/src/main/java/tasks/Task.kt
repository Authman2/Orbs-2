package tasks;

public class Task(var description: String, 
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