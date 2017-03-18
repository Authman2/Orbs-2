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
		
		if(key == KeyCode.UP) { direction = Direction.Up; targetPosition.add(new Vector2D(0,-1)); startedMoving = true; }
		else if(key == KeyCode.DOWN) { direction = Direction.Down; targetPosition.add(new Vector2D(0,1)); startedMoving = true;}
		else if(key == KeyCode.LEFT) { direction = Direction.Left; targetPosition.add(new Vector2D(-1,0)); startedMoving = true; }
		else if(key == KeyCode.RIGHT) { direction = Direction.Right; targetPosition.add(new Vector2D(1,0)); startedMoving = true; }
		
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
