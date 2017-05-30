package entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import je.visual.Vector2D;
import main_package.Animator;
import main_package.Assets;
import states.WorldState;

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


	// Handles the key actions
	public void keyActions(KeyCode code) {
		switch(code) {
		case UP:
			changeDirection(Direction.UP);
			yVel = -speed;
			break;
		case DOWN:
			changeDirection(Direction.DOWN);
			yVel = speed;
			break;
		case LEFT:
			changeDirection(Direction.LEFT);
			xVel = -speed;
			break;
		case RIGHT:
			changeDirection(Direction.RIGHT);
			xVel = speed;
			break;	
		default: break;
		}
	}

	// Handles key releases
	public void releaseKeyActions(KeyCode code) {
		switch(code) {
		case UP:
			yVel = 0;
			break;
		case DOWN:
			yVel = 0;
			break;
		case LEFT:
			xVel = 0;
			break;
		case RIGHT:
			xVel = 0;
			break;	
		default: break;
		}
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
		
		this.setSpeed(0.08f);
	}

	public void update() {
		super.update();
	}

	public void draw() {
		if(direction == Direction.UP) {
			if(this.moving == true) {
				this.drawSprite(walkUp.getCurrentSprite()); 
				this.walkUp.update();
			} else {
				this.drawSprite(this.sprites[0]);
			}
		} else if(direction == Direction.DOWN) {
			if(this.moving == true) {
				this.drawSprite(walkDown.getCurrentSprite()); 
				this.walkDown.update();
			} else {
				this.drawSprite(this.sprites[1]);
			}
		} else if(direction == Direction.LEFT) {
			if(this.moving == true) {
				this.drawSprite(walkLeft.getCurrentSprite()); 
				this.walkLeft.update();
			} else {
				this.drawSprite(this.sprites[2]);
			}
		} else if(direction == Direction.RIGHT) {
			if(this.moving == true) {
				this.drawSprite(walkRight.getCurrentSprite()); 
				this.walkRight.update();
			} else {
				this.drawSprite(this.sprites[3]);
			}
		}


        /* DRAW COLLISION BOX */
		this.worldState.getGraphics().strokeRect(collisionBox.getMinX(), collisionBox.getMinY(), size, size);
	}

	// Quick helper function for drawing a sprite.
	public void drawSprite(Image img) {
		this.worldState.getGraphics().drawImage(img, position.X*size, position.Y*size);
	}

} // End of class.