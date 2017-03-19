package com.adeolauthman.Orbs2.entities;

import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

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
		
		if(key == KeyCode.UP) { direction = Direction.Up; ws.getWorld().getMainCamera().moveCamera(direction); startedMoving = true; }
		else if(key == KeyCode.DOWN) { direction = Direction.Down; ws.getWorld().getMainCamera().moveCamera(direction); startedMoving = true;}
		else if(key == KeyCode.LEFT) { direction = Direction.Left; ws.getWorld().getMainCamera().moveCamera(direction); startedMoving = true; }
		else if(key == KeyCode.RIGHT) { direction = Direction.Right; ws.getWorld().getMainCamera().moveCamera(direction); startedMoving = true; }
		
		System.out.println(ws.getWorld().getMainCamera().getPosition());
	}
	

	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		
	}
	
	public void update() {
		
	}
	
	public void draw() {
		// If there is no sprite, just draw a black square.
		if(sprite == null) { graphics.fillRect(position.X*width, position.Y*height, width, height); }
		else { graphics.drawImage(sprite, position.X*width, position.Y*height); }
	}
	
	
	
}
