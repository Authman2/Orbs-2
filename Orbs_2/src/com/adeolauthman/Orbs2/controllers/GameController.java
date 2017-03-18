package com.adeolauthman.Orbs2.controllers;

import com.adeolauthman.Orbs2.sceneControllers.GameSceneController;
import com.adeolauthman.Orbs2.sceneControllers.MainMenuSceneController;
import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

/** This is equivalent to a GameStateManager. */
public class GameController {

	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/	
	
	Stage appWindow;
	int width, height;
	
	
	GameSceneController[] controllers;
	GameSceneController mainMenuController, worldSceneController;
	GameSceneController currentController;
	
	
	/************************
	 * 						*
	 * 		Constructor		*
	 * 						*
	 ************************/
	
	/**
	 * Creates a new GameController with a BorderPane to hold every scene and a width and height.
	 */
	public GameController(Stage window, int w, int h) {
		this.appWindow = window;
		this.width = w;
		this.height = h;
		
		// Initialize the scene controllers.
		mainMenuController = new MainMenuSceneController(this);
		worldSceneController = new WorldSceneController(this);
		controllers = new GameSceneController[] {mainMenuController, worldSceneController};
		
		// Set the first scene.
		currentController = mainMenuController;
		
		// Start the game loop.
		startGameLoop();
	}
	
	
	public void startGameLoop() {
		//final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
			public void handle(long now) {
				//double t = (System.nanoTime() - startNanoTime) / 1000000000.0; 
				 
	            update();
	            draw();
			}
		}.start();
	}
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	/**
	 * Changes scenes to the one at the index.
	 */
	public void changeScene(int index) {
		currentController = controllers[index];
		initialize();
	}
	
	
	public void initialize() {
		appWindow.setScene(currentController.getAssociatedScene());
		currentController.initialize();
	}
	
	public void update() {
		currentController.update();
	}
	
	public void draw() {
		currentController.draw();
	}
	
	
	
	
	
	/************************
	 * 						*
	 * 		 Getters		*
	 * 						*
	 ************************/
	
	public Stage getWindow() { return appWindow; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
}
