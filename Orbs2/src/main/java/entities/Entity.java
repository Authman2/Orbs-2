package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import je.visual.Vector2D;

import states.WorldState;

public abstract class Entity {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/
	
	protected WorldState worldState;

	public float xVel, yVel, speed;

	protected Vector2D position;

	protected Image sprite;

	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public Entity(Vector2D pos, WorldState ws) {
		this.worldState = ws;
		this.xVel = 0;
		this.yVel = 0;
		this.speed = 0.2f;
		this.position = pos;
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Constantly moves the entity based on its x and y velocities. */
	public void move() {
		position.X += xVel;
		position.Y += yVel;
	}


	public void setSprite(Image img) {
		this.sprite = img;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}



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

	}

	public void update() {
		move();
	}

	public void draw() {
		if(sprite != null) {
			this.worldState.getGraphics().drawImage(sprite, position.X*32, position.Y*32);
		}
	}

} // End of class.