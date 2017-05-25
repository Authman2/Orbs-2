package entities;

import je.visual.Vector2D;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

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
        this.setSprites( new Image[] { Assets.PLAYER_UP,
        							   Assets.PLAYER_DOWN,
        							   Assets.PLAYER_LEFT,
        							   Assets.PLAYER_RIGHT } );
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