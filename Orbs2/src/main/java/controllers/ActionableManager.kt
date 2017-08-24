package controllers

import states.*
import world.Camera
import entities.*
import controllers.Networking
import javafx.scene.image.Image
import kotlin.jvm.*
import tasks.*
import hud.*

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
    lateinit var doorBlock_1: ActionObject; lateinit var doorBlock_2: ActionObject
    lateinit var doorBlock_3: ActionObject; lateinit var doorBlock_4: ActionObject
    lateinit var doorBlock_5: ActionObject; lateinit var doorBlock_6: ActionObject
    lateinit var doorBlock_7: ActionObject; lateinit var doorBlock_8: ActionObject
    lateinit var doorBlock_9: ActionObject; lateinit var doorBlock_10: ActionObject
    lateinit var tree_1: ActionObject; lateinit var tree_2: ActionObject; 
    lateinit var tree_3: ActionObject; lateinit var tree_4: ActionObject; 
    lateinit var tree_5: ActionObject; lateinit var tree_6: ActionObject;
    lateinit var tree_7: ActionObject; lateinit var tree_8: ActionObject; 
    lateinit var tree_9: ActionObject; lateinit var tree_10: ActionObject;
    lateinit var tree_11: ActionObject; lateinit var tree_12: ActionObject;
    lateinit var tree_13: ActionObject; lateinit var tree_14: ActionObject; 
    lateinit var tree_15: ActionObject; lateinit var tree_16: ActionObject; 
    lateinit var tree_17: ActionObject; lateinit var tree_18: ActionObject;
    lateinit var tree_19: ActionObject; lateinit var tree_20: ActionObject; 
    lateinit var tree_21: ActionObject; lateinit var tree_22: ActionObject; 
    lateinit var tree_23: ActionObject; lateinit var tree_24: ActionObject;
    lateinit var tree_25: ActionObject; lateinit var tree_26: ActionObject;
    lateinit var tree_27: ActionObject; lateinit var tree_28: ActionObject;
    lateinit var tree_29: ActionObject; lateinit var tree_30: ActionObject;
    lateinit var tree_31: ActionObject; lateinit var tree_32: ActionObject;
    lateinit var tree_33: ActionObject; lateinit var tree_34: ActionObject;
    lateinit var tree_35: ActionObject; lateinit var tree_36: ActionObject;
    lateinit var tree_37: ActionObject; lateinit var tree_38: ActionObject;
    lateinit var tree_39: ActionObject; lateinit var tree_40: ActionObject;
    lateinit var tree_41: ActionObject; lateinit var tree_42: ActionObject;
    lateinit var tree_43: ActionObject; lateinit var tree_44: ActionObject;
    lateinit var tree_45: ActionObject; lateinit var tree_46: ActionObject;
    lateinit var tree_47: ActionObject; lateinit var tree_48: ActionObject;
    lateinit var tree_49: ActionObject; lateinit var tree_50: ActionObject;
    lateinit var tree_51: ActionObject; lateinit var tree_52: ActionObject;
    lateinit var tree_53: ActionObject; lateinit var tree_54: ActionObject;

    lateinit var generator: ActionObject
    lateinit var rock_1: ActionObject; lateinit var rock_2: ActionObject; 
    lateinit var rock_3: ActionObject; lateinit var rock_4: ActionObject; 
    lateinit var rock_5: ActionObject; lateinit var rock_6: ActionObject;
    lateinit var rock_7: ActionObject; lateinit var rock_8: ActionObject; 
    lateinit var rock_9: ActionObject; lateinit var rock_10: ActionObject;
    lateinit var rock_11: ActionObject; lateinit var rock_12: ActionObject;
    lateinit var rock_13: ActionObject; lateinit var rock_14: ActionObject; 
    lateinit var rock_15: ActionObject; lateinit var rock_16: ActionObject; 
    lateinit var rock_17: ActionObject; lateinit var rock_18: ActionObject;
    lateinit var rock_19: ActionObject; lateinit var rock_20: ActionObject; 
    lateinit var rock_21: ActionObject; lateinit var rock_22: ActionObject; 
    lateinit var rock_23: ActionObject; lateinit var rock_24: ActionObject;
    lateinit var rock_25: ActionObject; lateinit var rock_26: ActionObject; 
    lateinit var rock_27: ActionObject; lateinit var rock_28: ActionObject; 
    lateinit var rock_29: ActionObject; lateinit var rock_30: ActionObject;
    lateinit var rock_31: ActionObject; lateinit var rock_32: ActionObject; 
    lateinit var rock_33: ActionObject; lateinit var rock_34: ActionObject;
    lateinit var rock_35: ActionObject; lateinit var rock_36: ActionObject;
    lateinit var rock_37: ActionObject; lateinit var rock_38: ActionObject; 
    lateinit var rock_39: ActionObject; lateinit var rock_40: ActionObject; 
    lateinit var rock_41: ActionObject; lateinit var rock_42: ActionObject;
    lateinit var rock_43: ActionObject; lateinit var rock_44: ActionObject;



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
        doorBlock_1.getOptions().add("Jersey City")
        doorBlock_1.getOptions().add("Princeton")
        doorBlock_1.getOptions().add("Trenton")
        doorBlock_1.setCompletion {
            TextBox.finishedFunction = null
            if(it == "Trenton") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_1)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }
        doorBlock_2.cloneOptions(doorBlock_1);

        doorBlock_3.getOptions().add("12")
        doorBlock_3.getOptions().add("5")
        doorBlock_3.getOptions().add("9")
        doorBlock_3.setCompletion {
            TextBox.finishedFunction = null
            if(it == "12") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_3)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }
        doorBlock_4.cloneOptions(doorBlock_3)
        doorBlock_4.setCompletion {
            TextBox.finishedFunction = null
            if(it == "12") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_4)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }

        doorBlock_5.getOptions().add("1")
        doorBlock_5.getOptions().add("9")
        doorBlock_5.getOptions().add("7")
        doorBlock_5.setCompletion {
            TextBox.finishedFunction = null
            if(it == "9") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_5)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }
        doorBlock_6.cloneOptions(doorBlock_5)
        doorBlock_6.setCompletion {
            TextBox.finishedFunction = null
            if(it == "9") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_6)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }

        doorBlock_7.getOptions().add("Dreams")
        doorBlock_7.getOptions().add("Meals")
        doorBlock_7.getOptions().add("Bug Bites")
        doorBlock_7.setCompletion {
            TextBox.finishedFunction = null
            if(it == "Dreams") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_7)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }
        doorBlock_8.cloneOptions(doorBlock_7)
        doorBlock_8.setCompletion {
            TextBox.finishedFunction = null
            if(it == "Dreams") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_8)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }

        doorBlock_9.getOptions().add("United States")
        doorBlock_9.getOptions().add("China")
        doorBlock_9.getOptions().add("France")
        doorBlock_9.setCompletion {
            TextBox.finishedFunction = null
            if(it == "France") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_9)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }
        doorBlock_10.cloneOptions(doorBlock_9)
        doorBlock_10.setCompletion {
            if(it == "France") {
                worldState.getTextBox().set(arrayListOf<String>("Correct!")) 
                worldState.getTextBox().toggle();
                actionObjects.remove(doorBlock_10)
            } else {
                worldState.getTextBox().set(arrayListOf<String>("Incorrect!")) 
                worldState.getTextBox().toggle();
            }
        }

        // Define the collision area for each door.
        for(field in ActionableManager::class.java.getDeclaredFields()) {
            if(field.getName().contains("door")) {
                val gotten: ActionObject = field.get(this) as ActionObject
                gotten.defineCollisionArea( (gotten.position.X*32 + 18).toFloat() , (gotten.position.Y*32).toFloat(), (32 - 18).toFloat(), (32 - 8).toFloat())
            }
        }

        // Trees: Yes to cut down the tree, no to not.
        for(field in ActionableManager::class.java.getDeclaredFields()) {
            if(field.getName().contains("tree")) {
                val gotten: ActionObject = field.get(this) as ActionObject
                
                gotten.getOptions().add("Yes")
                gotten.getOptions().add("No")
                gotten.setCompletion {
                    TextBox.finishedFunction = null
                    if(it == "Yes") {
                        if(InventoryState.contains("HATCHET")) { actionObjects.remove(gotten) }
                        else {
                            worldState.getTextBox().set(arrayListOf<String>("You need a hatchet to cut down a tree.")) 
                            worldState.getTextBox().toggle();
                        }
                    }
                }
            }
        }

        // Rocks: Yes to break the rock, no to not.
        for(field in ActionableManager::class.java.getDeclaredFields()) {
            if(field.getName().contains("rock")) {
                val gotten: ActionObject = field.get(this) as ActionObject
                
                gotten.getOptions().add("Yes")
                gotten.getOptions().add("No")
                gotten.setCompletion {
                    TextBox.finishedFunction = null
                    if(it == "Yes") {
                        if(InventoryState.contains("PICKAXE")) { actionObjects.remove(gotten) }
                        else {
                            worldState.getTextBox().set(arrayListOf<String>("You need a pickaxe to break a rock.")) 
                            worldState.getTextBox().toggle();
                        }
                    }
                }
            }
        }

        // Generator task.
        generator.getOptions().add("Yes")
        generator.getOptions().add("No")
        generator.setCompletion {
            TextBox.finishedFunction = null
            if(it == "Yes") {
                worldState.getTextBox().set(
                    arrayListOf<String>("...", "... ...", "... ... ...", "You mess around with the machine a bit and finally get it to work!")
                )
                worldState.getTextBox().toggle()
                Player.generatorStarted = true

                if(TaskSystem.getTask("POWER_PLANT")!!.isStarted())
                    NPCManager.electricityMan.setSpeech(NPCManager.npcSpeech.get("electricityMan_2")!!)
            } else {
                worldState.getTextBox().set(
                    arrayListOf<String>("* You decide not to touch the machine. It seemed pretty complicated, anyway.*")
                )
                worldState.getTextBox().toggle();
            }
        }
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