package com.adeolauthman.Orbs2.entities;

import java.util.function.Function;

import com.adeolauthman.Orbs2.sceneControllers.GameSceneController;
import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import je.visual.Vector2D;

public abstract class Entity {
	
	public enum Direction { 
		Up(), Down(), Left(), Right(); 
	
		Direction() {}
		
		public Direction getOpposite(Entity ent) {
			Direction d = null;
			switch(ent.direction) {
				case Up: d = Down; break;
				case Down: d = Up; break;
				case Left: d = Right; break;
				case Right: d = Left; break;
			}
			return d;
		}
	}

	/************************
	 * 						*
	 * 	    Variables		*
	 * 						*
	 ************************/
	
	protected WorldSceneController ws;
	
	protected GraphicsContext graphics;
	
	protected Vector2D position, targetPosition;
	
	protected Image sprite;

	protected Direction direction = Direction.Down;
	
	protected int width = 32, height = 32;
	
	boolean moving, startedMoving;
	protected float moveSpeed = 0.2f;
	
	
	
	/************************
	 * 						*
	 * 	   Constructors		*
	 * 						*
	 ************************/
	
	public Entity(Image img, GameSceneController ws) {
		this.ws = (WorldSceneController)ws;
		this.graphics = ((WorldSceneController)ws).getGraphicsContext();
		this.sprite = img;
		position = new Vector2D();
		targetPosition = new Vector2D();
	}
	
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	/** Sets the position of the entity. */
	public void setPosition(float x, float y) { position.X = x; position.Y = y; }
	
	
	/** Sets the target position. */
	public void setTargetPos(float x, float y) { targetPosition.X = x; targetPosition.Y = y; }
	
	
	public void setStartMoving(boolean b) { startedMoving = b; }
	
	
	/** Sets the direction of the entity. */
	public void setDirection(Direction d) { this.direction = d; }
	

	/** Moves the entity to the target position. */
	public void move(float speed, Function<?,?> completion) {
		switch(direction) {
			case Right:
				if(position.X < targetPosition.X) { position.X += speed; moving = true; } 
				else { 
					position.X = targetPosition.X;
					if(completion != null && startedMoving == true) completion.apply(null);
					moving = false; startedMoving = false;
				}
				break;
			case Left:
				if(position.X > targetPosition.X) { position.X -= speed; moving = true; } 
				else { 
					position.X = targetPosition.X;
					if(completion != null && startedMoving == true) completion.apply(null);
					moving = false; startedMoving = false; 
				}
				break;
			case Up:
				if(position.Y > targetPosition.Y) { position.Y -= speed; moving = true; } 
				else { 
					position.Y = targetPosition.Y;
					if(completion != null && startedMoving == true) completion.apply(null);
					moving = false; startedMoving = false; 
				}
				break;
			case Down:
				if(position.Y < targetPosition.Y) { position.Y += speed; moving = true; } 
				else { 
					position.Y = targetPosition.Y;
					if(completion != null && startedMoving == true) completion.apply(null);
					moving = false; startedMoving = false; 
				}
				break;
			default:
				break;
		}
	}
	

	
	
	/************************
	 * 						*
	 * 		 Getters		*
	 * 						*
	 ************************/
	
	public Vector2D getPosition() { return position; }
	
	public Direction getDirection() { return direction; }
	
	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/

	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
}