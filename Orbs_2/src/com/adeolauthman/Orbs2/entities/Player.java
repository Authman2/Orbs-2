package com.adeolauthman.Orbs2.entities;

import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import je.visual.Vector2D;

public class Player extends Entity {

	
	/************************
	 * 						*
	 * 	   Constructors		*
	 * 						*
	 ************************/
	
	public Player(Image img, WorldSceneController ws) {
		super(img, ws);
	}
	
	
	
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	/** Sets the player's direction and new target position based on the key that was just clicked. */
	public void setKeyActions(KeyCode key) {
		
		if(key == KeyCode.UP) { direction = Direction.Up; targetPosition = new Vector2D(position.X, position.Y-1); startedMoving = true; }
		else if(key == KeyCode.DOWN) { direction = Direction.Down; targetPosition = new Vector2D(position.X, position.Y+1); startedMoving = true;}
		else if(key == KeyCode.LEFT) { direction = Direction.Left; targetPosition = new Vector2D(position.X-1, position.Y); startedMoving = true; }
		else if(key == KeyCode.RIGHT) { direction = Direction.Right; targetPosition = new Vector2D(position.X+1, position.Y); startedMoving = true; }
		
	}
	

	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		
	}
	
	public void update() {
		move(moveSpeed, e -> { 
			System.out.println("Finished walking");
			return null;
		});
	}
	
	public void draw() {
		// If there is no sprite, just draw a black square.
		if(sprite == null) { graphics.fillRect(position.X*width, position.Y*height, width, height); }
		else { graphics.drawImage(sprite, position.X*width, position.Y*height); }
	}
	
	
	
}
