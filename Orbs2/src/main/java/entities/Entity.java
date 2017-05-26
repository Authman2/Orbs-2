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

	public float size, xVel, yVel, speed;

	protected Vector2D position;

	protected Image[] sprites;

	protected Image currentSprite;

	protected Direction direction;


	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public Entity(Vector2D pos, WorldState ws) {
		this.worldState = ws;
		this.size = 32;
		this.xVel = 0;
		this.yVel = 0;
		this.speed = 0.15f;
		this.position = pos;
		this.sprites = new Image[4];
		this.direction = Direction.random();
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


	/** Changes the sprite of the entity based on its direction. */
	public void updateDirection() {
		switch(this.direction) {
			case UP: this.currentSprite = this.sprites[0]; break;
			case DOWN: this.currentSprite = this.sprites[1]; break;
			case LEFT: this.currentSprite = this.sprites[2]; break;
			case RIGHT: this.currentSprite = this.sprites[3]; break;
			default: this.currentSprite = this.sprites[1]; break;
		}
	}


	/** Changes the direction of the entity manually. */
	public void changeDirection(Direction dir) {
		this.direction = dir;
	}


	public void setSprites(Image[] sprites) {
		this.sprites = sprites;
		this.currentSprite = this.sprites[1];
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
		updateDirection();
	}

	public void draw() {
		if(sprites != null) {
			this.worldState.getGraphics().drawImage(currentSprite, position.X*size, position.Y*size);
		}
	}

} // End of class.