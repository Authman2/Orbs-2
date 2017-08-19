package controllers

import states.*
import world.Camera
import entities.*
import controllers.Networking
import javafx.scene.image.Image
import kotlin.jvm.*

import je.visual.Vector2D

class ActionableManager(val worldState: WorldState) {

    /********************
	*					*
	*	  VARIABLES	    *
	*					*
	********************/

    // The list of all the action objects in the game.
    val actionObjects: ArrayList<ActionObject> = arrayListOf()


    // The objects.
    lateinit var doorBlock_1: ActionObject
    lateinit var doorBlock_2: ActionObject
    lateinit var doorBlock_3: ActionObject
    lateinit var doorBlock_4: ActionObject
    lateinit var doorBlock_5: ActionObject
    lateinit var doorBlock_6: ActionObject
    lateinit var doorBlock_7: ActionObject
    lateinit var doorBlock_8: ActionObject
    lateinit var doorBlock_9: ActionObject
    lateinit var doorBlock_10: ActionObject



    /********************
	*					*
	*	INITIALIZATION	*
	*					*
	********************/

    init {
        this.loadObjects()
        this.setQandO()
    }


    // Sets the question and options for each action object.
    private fun setQandO() {
        // FIRST: load all of the questions.
        this.loadQs()

        // SECOND: set their options.
        doorBlock_1.getOptions().put("Jersey City", {
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_1.getOptions().put("Princeton", { 
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open();
        })
        doorBlock_1.getOptions().put("Trenton", { 
            worldState.getTextBox().set( arrayListOf<String>("Correct!", "Correct!") );
            worldState.getTextBox().open();
            actionObjects.remove(doorBlock_1)
            actionObjects.remove(doorBlock_2)
        })
        doorBlock_2.cloneOptions(doorBlock_1);

        doorBlock_3.getOptions().put("12", {  
            worldState.getTextBox().set( arrayListOf<String>("Correct!", "Correct!") );
            worldState.getTextBox().open();
            actionObjects.remove(doorBlock_3)
            actionObjects.remove(doorBlock_4)
        })
        doorBlock_3.getOptions().put("5", {
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_3.getOptions().put("9", {
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_4.cloneOptions(doorBlock_3)

        doorBlock_5.getOptions().put("1", {
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_5.getOptions().put("9", {
            worldState.getTextBox().set( arrayListOf<String>("Correct!", "Correct!") );
            worldState.getTextBox().open();
            actionObjects.remove(doorBlock_5)
            actionObjects.remove(doorBlock_6)
        })
        doorBlock_5.getOptions().put("7", {
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_6.cloneOptions(doorBlock_5)

        doorBlock_7.getOptions().put("Dreams",{
            worldState.getTextBox().set( arrayListOf<String>("Correct!", "Correct!") );
            worldState.getTextBox().open();
            actionObjects.remove(doorBlock_7)
            actionObjects.remove(doorBlock_8)
        })
        doorBlock_7.getOptions().put("Meals",{
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_7.getOptions().put("Bug Bites",{
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_8.cloneOptions(doorBlock_7)

        doorBlock_9.getOptions().put("United States",{
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_9.getOptions().put("China",{
            worldState.getTextBox().set( arrayListOf<String>("Incorrect!", "Incorrect!") );
            worldState.getTextBox().open(); 
        })
        doorBlock_9.getOptions().put("France",{
            worldState.getTextBox().set( arrayListOf<String>("Correct!", "Correct!") );
            worldState.getTextBox().open();
            actionObjects.remove(doorBlock_9)
            actionObjects.remove(doorBlock_10)
        })
        doorBlock_10.cloneOptions(doorBlock_9)
    }

    
    // Loads all of the actionable objects from the text file.
    private fun loadObjects() {
        val lines: ArrayList<String> = Networking.read("maps/ActionableLocations.txt")
        lines.forEach {
            val parts = it.split(" ")
            
            if(parts.count() >= 4) {
                val name: String = parts[0]
                val x: Int = Integer.parseInt(parts[1])
                val y: Int = Integer.parseInt(parts[2])
                val asset: Image = worldState.gc.getAssets().fromString(parts[3])

                try {
                    val field = ActionableManager::class.java.getDeclaredField(name);
                    field.setAccessible(true);

                    val obj: ActionObject = ActionObject(Vector2D(x.toFloat(), y.toFloat()), worldState);
                    obj.setSprites( arrayOf(asset, asset, asset, asset) );
                    field.set(this, obj);

                    try {
                        val gotten: ActionObject = field.get(this) as ActionObject
                        this.actionObjects.add(gotten);
                    } catch(ex: Exception) {
                        // Nothing.
                    }
                } catch(e: Exception) {
                    // Nothing.
                }
            }
        }
    }


    // Loads all the questions from the text file.
    private fun loadQs() {
        val lines: ArrayList<String> = Networking.read("maps/ActionableQandO.txt")
        lines.forEach {
            val parts = it.split(" - ")
            
            val name: String = parts[0]
            val question: String = parts[1]

            try {
                val field = ActionableManager::class.java.getDeclaredField(name);
                field.setAccessible(true);

                try {
                    val gotten: ActionObject = field.get(this) as ActionObject
                    gotten.setQuestion(question)

                } catch(ex: Exception) {
                    // Nothing.
                }
            } catch(e: Exception) {
                // Nothing.
            }
        }
    }



    /********************
	*					*
	*	   SETTERS		*
	*					*
	********************/




    /********************
	*					*
	*	   ABSTRACT		*
	*					*
	********************/

    fun initialize() {

    }


    fun update(camera: Camera) {
        actionObjects.forEach {
            if(camera.touching(it)) {
                it.update()
            }
        }
    }


    fun draw(camera: Camera) {
        actionObjects.forEach {
            if(camera.touching(it)) {
                it.draw()
            }
        }
    }


}