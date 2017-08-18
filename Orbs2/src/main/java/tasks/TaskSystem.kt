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
            Task(id = "SPEAK_TO_SCIENTIST", description = "Speak to the scientist in his laboratory.").start(),
            Task(id = "CHARGE_ORBS", description = "Take all 10 orbs to their charging stations so that you can return them to the scientist."),
            Task(id = "BUCKET_OF_WATER", description = "Bring a bucket of water to the eldery woman."),
            Task(id = "ROPE", description = "Buy a rope so you can help the little boy out of the well."),
            Task(id = "MISSING_DOG", description = "Find the missing dog and bring it back to the dog park."),
            Task(id = "SWIMMING_LESSONS", description = "Take swimming lessons at the recreational facility."),
            Task(id = "MUSEUM_KEY", description = "Find the key required to enter the back room of the museum."),
            Task(id = "COOKIES", description = "Bring party snacks to the desperate mother for her daughter's birthday party."),
            Task(id = "POWER_PLANT", description = "Restart the generator in the power plant to help the worker fix the town's electrical problem."),
            Task(id = "WOOD_TRANSPORTER", description = "Figure out what happened to the man who was supposed to transport the chopped wood in his pickup truck."),
            Task(id = "BROKEN_DOWN_TRUCK", description = "Bring some tools to the man with the broken down truck."),
            Task(id = "OIL_ISSUE", description = "Distract the townspeople while the owner of the repair shop cleans up an oil issue."),
            Task(id = "LUMBERJACK_MESSAGE", description = "Deliver the message back to the lumberjack."),
            Task(id = "SPACESUIT", description = "Find a spacesuit so you can enter the space station."),
            Task(id = "ASTRONAUT_CODE", description = "Find the code to the safe somewhere in the retired astronaut's house."),
            Task(id = "HORSE", description = "Rent a horse so you can enter the horse race."),
            Task(id = "BALES_OF_HAY", description = "Bring some bales of hay to the horses on the ranch."),
            Task(id = "DRILL", description = "See if you can find someone with a powerful drill to help the miners create their tunnel."),
            Task(id = "CEO", description = "Convince the powerful CEO's employee to come back to work."),
            Task(id = "EMPTY_HOUSE", description = "Follow the directions on the note you found in the empty house."),
            Task(id = "DEPARTMENT_STORE", description = "Figure out what's going on at the department store."),
            Task(id = "DEPT_CODE", description = "Try to see if anyone knows the secret code at the department store."),
            Task(id = "GIFT", description = "Buy a gift for the elderly man's grandson."),
            Task(id = "CRIME", description = "Get to the bottom of the crime that is taking place on the roof of the department store."),
            Task(id = "APPLES", description = "Help the chef with his dinner! Bring him some apples."),
            Task(id = "CORN", description = "Help the chef with his dinner! Bring him some corn."),
            Task(id = "LETTUCE", description = "Help the chef with his dinner! Bring him some lettuce."),
            Task(id = "TURKEY", description = "Help the chef with his dinner! Bring him some turkey."),
            Task(id = "BREAD", description = "Help the chef with his dinner! Bring him some bread.")
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


        @JvmStatic
        public fun getTask(id: String): Task? {
            val task = tasks.filter { it.getID() == id }.get(0);
            return task
        }


        @JvmStatic
        public fun getAllTasks(): ArrayList<Task> = tasks


        /********************
        *					*
        *	   SETTERS		*
        *					*
        *********************/

        /** Adds a new task to the global task system. */
        @JvmStatic
        public fun addTask(desc: String, id: String, depends: Task? = null, comp: (()->Unit)? = null) {
            val task = Task(desc, id, depends, comp)
            tasks.add(task);
        }


    }
    // End of companion object.
}
// End of class.