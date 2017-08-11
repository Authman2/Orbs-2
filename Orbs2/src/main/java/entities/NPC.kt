package entities

import je.visual.Vector2D
import states.WorldState
import main_package.*;
import javafx.animation.AnimationTimer

class NPC(pos: Vector2D, ws: WorldState) : Entity(pos, ws) {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	var solid: Boolean = true
	
	var timer: Long = 0



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	init {
		val sprites = arrayOf( Assets.SCIENTIST_UP, Assets.SCIENTIST_DOWN,
							Assets.SCIENTIST_LEFT, Assets.SCIENTIST_RIGHT )
		this.setSprites(sprites)
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