package com.adeolauthman.Orbs2.main;

import com.adeolauthman.Orbs2.controllers.GameController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Orbs2 extends Application {

	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Orbs 2");
		
		// Now make a game controller that handles game scenes, game elements, etc. Basically the entire game.
		GameController gc = new GameController(primaryStage, 640, 480);
		gc.initialize();
		
		primaryStage.show();
	}
	
	
}
