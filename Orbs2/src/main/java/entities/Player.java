package entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import je.visual.Vector2D;
import main_package.Animator;
import main_package.Assets;
import states.WorldState;
import tasks.TaskSystem;

public class Player extends Entity {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The animators.
	Animator walkUp, walkDown, walkLeft, walkRight;

	// booleans for movement (only for moving in the opposite direction)
	boolean up, down, left, right;
	


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

	/** Sets the walking animations. */
	private void setWalkingSprites() {
		// The walking animation.
		Image[] up, down, left, right;

		up = new Image[] { Assets.PLAYER_WALK_UP_1, Assets.PLAYER_WALK_UP_2, Assets.PLAYER_UP };
		down = new Image[] { Assets.PLAYER_WALK_DOWN_1, Assets.PLAYER_WALK_DOWN_2, Assets.PLAYER_DOWN };
		left = new Image[] { Assets.PLAYER_WALK_LEFT_1, Assets.PLAYER_WALK_LEFT_2, Assets.PLAYER_LEFT };
		right = new Image[] { Assets.PLAYER_WALK_RIGHT_1, Assets.PLAYER_WALK_RIGHT_2, Assets.PLAYER_RIGHT };
	
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


	/** Handles the key actions */
	public void keyActions(KeyCode code) {
		switch(code) {
		case UP:
			changeDirection(Direction.UP);
			up = true;
			yVel = -speed;
			break;
		case DOWN:
			changeDirection(Direction.DOWN);
			down = true;
			yVel = speed;
			break;
		case LEFT:
			changeDirection(Direction.LEFT);
			left = true;
			xVel = -speed;
			break;
		case RIGHT:
			changeDirection(Direction.RIGHT);
			right = true;
			xVel = speed;
			break;	
		default: break;
		}
	}

	
	/** Handles key releases */
	public void releaseKeyActions(KeyCode code) {
		switch(code) {
		case UP:
			yVel = 0;
			up = false;
			break;
		case DOWN:
			yVel = 0;
			down = false;
			break;
		case LEFT:
			xVel = 0;
			left = false;
			break;
		case RIGHT:
			xVel = 0;
			right = false;
			break;	
		default: break;
		}
	}
	
	
	/** Checks for collisions with solid objects */
	private void checkCollision() {
		if(worldState.getCurrentWorld().getTilesL1().stream().anyMatch( tile -> worldState.getCurrentWorld().getCamera().touching(tile)
																				&& tile.isSolid() 
																				&& tile.collidingWith(this))
			|| worldState.getCurrentWorld().getTilesL2().stream().anyMatch( tile -> worldState.getCurrentWorld().getCamera().touching(tile)
																				&& tile.isSolid() 
																				&& tile.collidingWith(this))
			)
		{
			xVel = 0;
			yVel = 0;
			moveOpposite();
		}
	}
	
	
	/** Moves the player in the opposite of its current direction. */
	private void moveOpposite() {
		if(up) { position.Y = position.Y + speed; }
		if(down) { position.Y = position.Y - speed; }
		if(left) { position.X = position.X + speed; }
		if(right) { position.X = position.X - speed; }
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
		this.setSpeed(0.05f); // 0.05f should be the default speed. It works nicely at 60fps.

		System.out.println( TaskSystem.getTask("CHARGE_ORBS").toString() );
	}

	public void update() {
		super.update();
		
		// This needs to be in update() for the player only because the player is actually moving.
		this.defineCollisionArea(position.X*size + 5, position.Y*size + 15, size - 10, size - 15);
		
		this.checkCollision();
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

		// DRAWING COLLISION BOX
		if(drawCollisionBox) {
			this.worldState.getGraphics().strokeRect(collisionBox.getMinX(), 
													collisionBox.getMinY(), 
													collisionBox.getWidth(), 
													collisionBox.getHeight());
		}
	}

	
	
	
	/********************
	*					*
	*	   HELPER		*
	*					*
	*********************/
	
	// Quick helper function for drawing a sprite.
	public void drawSprite(Image img) {
		this.worldState.getGraphics().drawImage(img, position.X*size, position.Y*size);
	}

} // End of class.