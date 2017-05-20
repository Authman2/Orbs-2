package states;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import javafx.scene.control.*;

import main_package.Orbs2;
import views.PillButton;
import controllers.GameController;

public class MainMenuState extends GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	HBox titleArea;
	VBox menuButtonsArea;
	Label titleLabel;
	PillButton startGameBtn, controlsBtn, exitBtn;




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public MainMenuState(GameController gc, Stage stage) {
		super(gc, stage);

		titleArea = new HBox();
		menuButtonsArea = new VBox();
		titleLabel = new Label("  Orbs \n 2");
		startGameBtn = new PillButton("Start Game");
		controlsBtn = new PillButton("Controls");
		exitBtn = new PillButton("Exit");

		this.setupMenu();
	}


	private void setupMenu() {
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);

		// Menus
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        // Menu Items
        MenuItem saveGame = new MenuItem("Save Game"),
        		 loadGame = new MenuItem("Load Game"), 
        		 exitGame = new MenuItem("Quit Game");
		saveGame.setOnAction(e -> {
			System.out.println("Saved the game!");
		});
		loadGame.setOnAction(e -> {
			System.out.println("Loading a game.");
		});
		exitGame.setOnAction(e -> { System.exit(0); });


		// Add all the menu items.
		fileMenu.getItems().addAll(saveGame, exitGame);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        root.getChildren().addAll(menuBar);
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	private void goToGame() {
		gc.goTo(1);
 	}
	
	private void goToControls() {
		gc.goTo(2);
	}

	private void exitApp() {
		System.exit(0);
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/




	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

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
		BorderPane holder = new BorderPane();
		holder.setTop(titleArea);
		holder.setCenter(menuButtonsArea);


		// IMPORTANT: Scene Setup
		root.getChildren().addAll(holder);
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
	}

	public void update() {
		//System.out.println("working");
	}

	public void draw() {

	}



} // End of class.