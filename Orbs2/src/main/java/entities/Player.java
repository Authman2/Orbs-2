package entities;

import je.visual.Vector2D;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import states.WorldState;
import main_package.*;

public class Player extends Entity {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The animators.
	Animator walkUp, walkDown, walkLeft, walkRight;



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
        this.setWalkingSprites();
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	private void setWalkingSprites() {
		// The walking animation.
		Image[] up, down, left, right;

		up = new Image[] { Assets.PLAYER_WALK_UP_1, Assets.PLAYER_UP, Assets.PLAYER_WALK_UP_2 };
		down = new Image[] { Assets.PLAYER_WALK_DOWN_1, Assets.PLAYER_DOWN, Assets.PLAYER_WALK_DOWN_2 };
		left = new Image[] { Assets.PLAYER_WALK_LEFT_1, Assets.PLAYER_LEFT, Assets.PLAYER_WALK_LEFT_2 };
		right = new Image[] { Assets.PLAYER_WALK_RIGHT_1, Assets.PLAYER_RIGHT, Assets.PLAYER_WALK_RIGHT_2 };
	
		this.walkUp = new Animator(up);
		this.walkDown = new Animator(down);
		this.walkLeft = new Animator(left);
		this.walkRight = new Animator(right);

		this.walkUp.setSpeed(180);
		this.walkDown.setSpeed(180);
		this.walkLeft.setSpeed(180);
		this.walkRight.setSpeed(180);

		this.walkUp.play();
		this.walkDown.play();
		this.walkLeft.play();
		this.walkRight.play();
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
		super.initialize();
	}

	public void update() {
		super.update();
	}

	public void draw() {
		if(direction == Direction.UP) {
			if(worldState.getCurrentWorld().isMoving() == true) {
				this.drawSprite(walkUp.getCurrentSprite()); 
				this.walkUp.update();
			} else {
				this.drawSprite(this.sprites[0]);
			}
		} else if(direction == Direction.DOWN) {
			if(worldState.getCurrentWorld().isMoving() == true) {
				this.drawSprite(walkDown.getCurrentSprite()); 
				this.walkDown.update();
			} else {
				this.drawSprite(this.sprites[1]);
			}
		} else if(direction == Direction.LEFT) {
			if(worldState.getCurrentWorld().isMoving() == true) {
				this.drawSprite(walkLeft.getCurrentSprite()); 
				this.walkLeft.update();
			} else {
				this.drawSprite(this.sprites[2]);
			}
		} else if(direction == Direction.RIGHT) {
			if(worldState.getCurrentWorld().isMoving() == true) {
				this.drawSprite(walkRight.getCurrentSprite()); 
				this.walkRight.update();
			} else {
				this.drawSprite(this.sprites[3]);
			}
		}
	}

	// Quick helper function for drawing a sprite.
	public void drawSprite(Image img) {
		this.worldState.getGraphics().drawImage(img, position.X*size, position.Y*size);
	}

} // End of class.