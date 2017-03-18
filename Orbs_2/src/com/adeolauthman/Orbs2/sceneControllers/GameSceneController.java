package com.adeolauthman.Orbs2.sceneControllers;

import com.adeolauthman.Orbs2.controllers.GameController;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class GameSceneController {

	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	GameController gc;
	Stage appWindow;
	BorderPane holder;
	Scene scene;
	
	
	
	/************************
	 * 						*
	 * 	   Constructors		*
	 * 						*
	 ************************/
	
	public GameSceneController(GameController gc) {
		this.gc = gc;
		this.holder = new BorderPane();
		this.appWindow = gc.getWindow();
		scene = new Scene(holder, gc.getWidth(), gc.getHeight());
	}
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/

	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	
	
	/************************
	 * 						*
	 * 	     Getters		*
	 * 						*
	 ************************/
	
	public Scene getAssociatedScene() { return scene; }
	public Stage getAppicationWindow() { return appWindow; }
}
