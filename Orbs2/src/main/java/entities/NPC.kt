package entities

import je.visual.Vector2D
import states.WorldState
import main_package.*;
import javafx.animation.AnimationTimer
import hud.*
import javafx.util.Pair
import java.util.function.Function

class NPC(pos: Vector2D, ws: WorldState) : Entity(pos, ws) {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The name of the npc.
	var name: String = ""

	// Whether or not the npc is solid (usually will be).
	var solid: Boolean = true
	
	// The timer for when the npc should turn in a different direction.
	var timer: Long = 0

	// All the the text that an npc will say.
	val speech: ArrayList<String> = arrayListOf()

	// The function to pass to the textbox for when you are done speaking to this npc.
	var finishedFunction: Function<NPC,NPC>? = null




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	init {
		
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	fun isSolid(): Boolean = this.solid





	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Sets what this npc should say. */
	fun setSpeech(text: ArrayList<String>) {
		this.speech.clear();
		this.speech.addAll(text);
	}

	


	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	override fun initialize() {
		super.initialize();
	}

	override fun update() {
		super.update();

		// Move in a different, random location to make the NPC more realistic.
		timer += 1
		if(timer > 100) {
			changeDirection(Direction.random())
			timer = 0
		}
	}

	override fun draw() {
		super.draw();
	}

}