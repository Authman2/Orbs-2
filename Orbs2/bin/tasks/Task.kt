package tasks;

public class Task {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

    /** A description of the task. Just to read what the task is. */
    private String description;


    /** Whether or not the task has even been stared. */
    private boolean started;


    /** Whether or not the task has been completed. */
    private boolean completed;


    /** The task that this task depends on to start. If that task is not completed, then
     * this one cannot be started. */
    private Task dependsOnTask;






    /********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

    public Task(String descriptionString) {
        this.description = descriptionString;
        this.started = false;
        this.completed = false;
    }

    public Task(String descriptionString, Task dO) {
        this.description = descriptionString;
        this.started = false;
        this.completed = false;
        this.dependsOnTask = dO;
    }



    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

    /** Returns whether or not this task has been started. */
    public boolean isStarted() { return this.started; }


    /** Returns whether or not this task has been completed. */
    public boolean isCompleted() { return this.completed; }

    
    /** Returns the description of the task. */
    public String getDescription() { return this.description; }




    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    /** Sets the task that this task requires to be completed before starting. */
    public void setDependsOn(Task t) {
        this.dependsOnTask = t;
    }


    /** Handles starting the first task. If this task depends on another one to be completed first
     * it will throw an exception.
    */
    public void start() throws Exception {
        // If there is no task to depend on...
        if(this.dependsOnTask == null) {
            this.started = true;
        } else {
            // Otherwise check if it is completed.
            if(this.dependsOnTask.isCompleted()) {
                this.started = true;
            } else {
                throw new Exception("Another task must be completed before starting this one. Please complete: " 
                                    + this.dependsOnTask.getDescription());
            }
        }
    }

    
    /** Sets the task to completed. */
    public void complete() {
        this.completed = true;
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

    }


} // End of class