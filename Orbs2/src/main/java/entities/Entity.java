package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
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
	
	protected boolean moving;

	protected Vector2D position;

	protected Image[] sprites;

	protected Image currentSprite;

	public Direction direction;

	protected Rectangle2D collisionBox, renderBox;
	protected float definedX, definedY, definedWidth, definedHeight;

	public static boolean drawCollisionBox = false, drawRenderBox = false;
	
	

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
		this.moving = false;
		this.position = pos;
		this.sprites = new Image[4];
		this.direction = Direction.random();
		this.definedX = position.X*size;
		this.definedY = position.Y*size;
		this.definedWidth = size;
		this.definedHeight = size;
		this.renderBox = new Rectangle2D(position.X*size, position.Y*size, size, size);
		this.collisionBox = new Rectangle2D(definedX, definedY, definedWidth, definedHeight);
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


	/** Directly changes an entity's position. */
	public void setPosition(float x, float y) {
		this.position.X = x;
		this.position.Y = y;
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
		if(!worldState.getMenu().isOpen()) {
			this.direction = dir;
		}
	}


	/** Sets the sprites used for each direction. */
	public void setSprites(Image[] sprites) {
		this.sprites = sprites;
		this.currentSprite = this.sprites[1];
	}


	/** Sets the move speed of the entity. */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	/** Defines the area that should be used for collision detection. */
	public void defineCollisionArea(float x, float y, float w, float h) {
		definedX = x;
		definedY = y;
		definedWidth = w;
		definedHeight = h;
		this.collisionBox = new Rectangle2D(definedX, definedY, definedWidth, definedHeight);
	}
	



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	/** Returns the position of the entity. */
	public Vector2D getPosition() {
		return position;
	}
	
	
	/** Returns this entity's collision box. */
	public Rectangle2D getCollisionBox() {
	    return collisionBox;
    }
	
	
	/** Returns the rendering box of this entity. Not to be confused with its collision box. */
	public Rectangle2D getRenderBox() {
		return renderBox;
	}


    /** Returns whether or not this entity is colliding with another. */
    public boolean collidingWith(Entity ent) {
    	return this.collisionBox.intersects( ent.getCollisionBox() );
    }

    
    /** Returns whether or not this entity is colliding with another. */
    public boolean collidingWith(Rectangle2D rect) {
        return this.collisionBox.intersects( rect );
    }


    /** Returns whether or not this entity is next to another. */
    public boolean nextTo(Entity ent) {
    	return position.Distance(ent.position) <= 1.5;
    }







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
		
		if(xVel == 0 && yVel == 0) { moving = false; } else { moving = true; }
	}

	public void draw() {
		if(sprites != null) {
            this.worldState.getGraphics().drawImage(currentSprite, position.X * size, position.Y * size);
        }
		if(drawCollisionBox) {
			this.worldState.getGraphics().strokeRect(collisionBox.getMinX(), 
													collisionBox.getMinY(), 
													collisionBox.getWidth(), 
													collisionBox.getHeight());
		}
		if(drawRenderBox) {
			this.worldState.getGraphics().strokeRect(renderBox.getMinX(), 
													renderBox.getMinY(), 
													renderBox.getWidth(), 
													renderBox.getHeight());
		}
	}

} // End of class.