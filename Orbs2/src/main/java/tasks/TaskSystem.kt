package tasks

import java.util.ArrayList

public class TaskSystem() {

    companion object TaskSystem {
        
        /********************
        *					*
        *	  VARIABLES		*
        *					*
        *********************/
        
        @JvmStatic
        public var tasks: ArrayList<Task> = arrayListOf( 
            Task(description = "Speak to the scientist in his laboratory.").start()
         );


        /********************
        *					*
        *	   GETTERS		*
        *					*
        *********************/

        /** Returns the task at the specified index. */
        @JvmStatic
        public fun getTask(index: Int): Task? {
            return if(tasks.size >= index) tasks.get(index) else null
        }




        /********************
        *					*
        *	   SETTERS		*
        *					*
        *********************/

        /** Adds a new task to the global task system. */
        @JvmStatic
        public fun addTask(desc: String, depends: Task? = null, comp: (()->Unit)? = null) {
            val task = Task(desc, depends, comp)
            tasks.add(task);
        }
    

    }
    // End of companion object.
}
// End of class.