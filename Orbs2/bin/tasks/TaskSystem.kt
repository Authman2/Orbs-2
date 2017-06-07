package tasks

import java.util.ArrayList

public class TaskSystem() {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

    companion object TaskSystem {
        public var tasks: ArrayList<Task> = arrayListOf();
    }



    /********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

    init {
        this.createInitialTasks()
    }

    private fun createInitialTasks() {
        this.addTask("Speak to the scientist in his laboratory.");
    }



    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/





    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    /** Adds a new task to the global task system. */
    public fun addTask(desc: String, depends: Task? = null) {
        val task = if(depends == null) Task(desc) else Task(desc, depends)
        tasks.add(task);
    }


}
// End of class.