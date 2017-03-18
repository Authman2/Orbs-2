package com.adeolauthman.Orbs2.sceneControllers;

import com.adeolauthman.Orbs2.controllers.GameController;
import com.adeolauthman.Orbs2.views.PillButton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MainMenuSceneController extends GameSceneController {

	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	HBox titleArea;
	VBox menuButtonsArea;
	Label titleLabel;
	PillButton startGameBtn;
	PillButton controlsBtn;
	PillButton exitBtn;
	
	
	
	/************************
	 * 						*
	 * 		Constructor		*
	 * 						*
	 ************************/
	
	public MainMenuSceneController(GameController gc) {
		super(gc);
		titleArea = new HBox();
		menuButtonsArea = new VBox();
		titleLabel = new Label("  Orbs \n 2");
		startGameBtn = new PillButton("Start Game");
		controlsBtn = new PillButton("Controls");
		exitBtn = new PillButton("Exit");
	}
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	private void goToGame() { gc.changeScene(1); }
	private void goToControls() { gc.changeScene(0); }
	private void exitApp() { getAppicationWindow().close(); }
	
	

	public void initialize() {
		// Set element properties.
		titleLabel.setTextAlignment(TextAlignment.CENTER);
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		
		startGameBtn.setMinSize(100, 40);
		controlsBtn.setMinSize(85, 40);
		exitBtn.setMinSize(70, 40);
		startGameBtn.getStyleClass().add("MainMenuButton");
		controlsBtn.getStyleClass().add("MainMenuButton");
		exitBtn.getStyleClass().add("MainMenuButton");
		
		startGameBtn.setOnAction(e -> { goToGame(); });
		controlsBtn.setOnAction(e -> { goToControls(); });
		exitBtn.setOnAction(e-> { exitApp(); });
		
		// Add everything.
		titleArea.setAlignment(Pos.CENTER);
		HBox.setMargin(titleLabel, new Insets(30,0,0,0));
		titleArea.getChildren().add(titleLabel);
		
		menuButtonsArea.setAlignment(Pos.CENTER);
		VBox.setMargin(startGameBtn, new Insets(0,0,15,0));
		VBox.setMargin(controlsBtn, new Insets(0,0,15,0));
		VBox.setMargin(exitBtn, new Insets(0,0,15,0));
		menuButtonsArea.getChildren().addAll(startGameBtn, controlsBtn, exitBtn);
		
		
		// Set the holder properties.
		holder.setTop(titleArea);
		holder.setCenter(menuButtonsArea);
	}

	public void update() { }

	public void draw() { }

}
