package entities;

import je.visual.Vector2D;
import javafx.scene.input.KeyCode;

import states.WorldState;
import main_package.Assets;

public class Player extends Entity {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/


	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public Player(Vector2D pos, WorldState ws) {
		super(pos, ws);
        this.setSprite(Assets.GRASS_1);
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/


	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		super.initialize();
	}

	public void update() {
		super.update();
	}

	public void draw() {
		super.draw();
	}

} // End of class.