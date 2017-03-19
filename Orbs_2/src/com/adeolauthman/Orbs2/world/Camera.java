package com.adeolauthman.Orbs2.world;

import com.adeolauthman.Orbs2.entities.Entity;
import com.adeolauthman.Orbs2.entities.Entity.Direction;

import je.visual.Vector2D;

public class Camera {
	
	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	World world;
	
	Vector2D position;
	
	Entity[] children;
	
	Entity centeredOn;
	
	double width, height;
	
	
	
	/************************
	 * 						*
	 * 		Constructor		*
	 * 						*
	 ************************/
	
	public Camera(World world) {
		position = new Vector2D(0,0);
		this.world = world;
		this.width = world.worldScene.getAssociatedScene().getWidth(); this.height = world.worldScene.getAssociatedScene().getHeight();
	}
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	/** Adds children to the camera view. */
	public void addChildren(Entity... ents) {
		this.children = ents;
	}
	
	
	/** Sets the entity that this camera should center on. */
	public void setCenterEntity(Entity ent) {
		this.centeredOn = ent;
	}
	
	
	/** Moves the camera in a particular direction and moves all of the entities in the camera. */
	public void moveCamera(Direction dir) {
		switch(dir) {
			case Up: position.Y += 1; for(Entity ent : children) { ent.setPosition(ent.getPosition().X, ent.getPosition().Y + 1); }; break;
			case Down: position.Y -= 1; for(Entity ent : children) { ent.setPosition(ent.getPosition().X, ent.getPosition().Y - 1); }; break;
			case Right: position.X -= 1; for(Entity ent : children) { ent.setPosition(ent.getPosition().X - 1, ent.getPosition().Y); }; break;
			case Left: position.X += 1; for(Entity ent : children) { ent.setPosition(ent.getPosition().X + 1, ent.getPosition().Y); }; break;
		}
	}
	
	
	/************************
	 * 						*
	 * 		 Getters		*
	 * 						*
	 ************************/
	
	public Vector2D getPosition() { return position; }
	
	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		
	}
	
	public void update() {
		//System.out.println(String.format("Camera Info: %s, %s, %s", position.toString(), ""+width, ""+height));
		
	}
	
	public void draw() {
//		world.worldScene.getGraphicsContext().setStroke(Paint.valueOf("red"));
//		world.worldScene.getGraphicsContext().strokeRect(position.X, position.Y, width, height);
		
		if(children != null) {
			
			for(Entity ent : children) {
				if(ent.getPosition().X >= position.X && ent.getPosition().X*32 < width) {
					if(ent.getPosition().Y >= position.Y && ent.getPosition().Y*32 < height) {
						ent.draw();
					}
				}
			}
			
		}
	}
	
}
