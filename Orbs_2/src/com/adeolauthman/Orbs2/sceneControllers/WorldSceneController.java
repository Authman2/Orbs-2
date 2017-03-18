package com.adeolauthman.Orbs2.sceneControllers;

import com.adeolauthman.Orbs2.controllers.GameController;
import com.adeolauthman.Orbs2.entities.Player;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class WorldSceneController extends GameSceneController {

	/************************
	 * 						*
	 * 	    Variables		*
	 * 						*
	 ************************/
	
	Canvas canvas;
	GraphicsContext graphics;
	
	Player player;
	
	
	
	/************************
	 * 						*
	 * 	   Constructors		*
	 * 						*
	 ************************/
	
	public WorldSceneController(GameController gc) {
		super(gc);
		canvas = new Canvas(scene.getWidth(), scene.getHeight());
		graphics = canvas.getGraphicsContext2D();
		
		player = new Player(null, this);
		player.setPosition(10, 8);
	}

	
	
	/************************
	 * 						*
	 * 	   	 Setters		*
	 * 						*
	 ************************/
	
	/** Clears the canvas frame. */
	public void clearFrame() {
		graphics.clearRect(0, 0, scene.getWidth(), scene.getHeight());
	}
	
	
	
	/************************
	 * 						*
	 * 	   	 Getters		*
	 * 						*
	 ************************/
	
	public GraphicsContext getGraphicsContext() { return graphics; }
	
	
	
	
	/************************
	 * 						*
	 * 	   	 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		StackPane p = new StackPane();
		p.getChildren().add(canvas);
		holder.setCenter(p);
		
		// Add all key events.
		scene.setOnKeyPressed(e -> {
			KeyCode w = e.getCode();
			
			player.setKeyActions(w);
		});
	}

	public void update() { 
		player.update();
	}

	public void draw() {
		clearFrame();
		
		player.draw();
	}

}
