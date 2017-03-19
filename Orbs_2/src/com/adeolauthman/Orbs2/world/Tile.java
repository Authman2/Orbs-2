package com.adeolauthman.Orbs2.world;

import com.adeolauthman.Orbs2.entities.Entity;
import com.adeolauthman.Orbs2.sceneControllers.GameSceneController;

import javafx.scene.image.Image;

public class Tile extends Entity {

	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	boolean solid;
	
	
	
	/************************
	 * 						*
	 * 	   Constructors		*
	 * 						*
	 ************************/
	
	public Tile(Image img, GameSceneController ws) {
		super(img, ws);
	}
	
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	public void setSolid(boolean b) { this.solid = b; }
	
	
	
	
	/************************
	 * 						*
	 * 		 Getters		*
	 * 						*
	 ************************/
	
	public boolean isSolid() { return solid; }
	
	
	
	
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
		graphics.drawImage(sprite, position.X*width, position.Y*height);
	}
	
	
	
}
