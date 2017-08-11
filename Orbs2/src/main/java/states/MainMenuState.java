package states;

import controllers.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main_package.Orbs2;
import views.PillButton;

public class MainMenuState extends GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	HBox titleArea;
	VBox menuButtonsArea;
	Label titleLabel;
	PillButton newGameBtn, loadGameBtn, controlsBtn, exitBtn;




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
		newGameBtn = new PillButton("New Game");
		loadGameBtn = new PillButton("Load Game");
		controlsBtn = new PillButton("Controls");
		exitBtn = new PillButton("Exit");

		this.setupMenu();
		this.configureLayout();
	}


	private void setupMenu() {
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);

		// Menus
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        // Menu Items
        MenuItem saveGame = new MenuItem("Save Game"),
        		 exitGame = new MenuItem("Quit Game");
		saveGame.setOnAction(e -> {
			( (WorldState) this.gc.getStates()[1] ).saveGame();
		});
		exitGame.setOnAction(e -> { System.exit(0); });


		// Add all the menu items.
		fileMenu.getItems().addAll(saveGame, exitGame);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        root.getChildren().addAll(menuBar);
	}

	private void configureLayout() {
		// Set element properties.
		titleLabel.setTextAlignment(TextAlignment.CENTER);
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		
		newGameBtn.setMinSize(100, 40);
		loadGameBtn.setMinSize(100, 40);
		controlsBtn.setMinSize(85, 40);
		exitBtn.setMinSize(70, 40);
		newGameBtn.getStyleClass().add("MainMenuButton");
		loadGameBtn.getStyleClass().add("MainMenuButton");
		controlsBtn.getStyleClass().add("MainMenuButton");
		exitBtn.getStyleClass().add("MainMenuButton");
		
		newGameBtn.setOnAction(e -> { newGame(); });
		loadGameBtn.setOnAction(e -> { loadGame(); });
		controlsBtn.setOnAction(e -> { goToControls(); });
		exitBtn.setOnAction(e-> { exitApp(); });
		
		// Add everything.
		titleArea.setAlignment(Pos.CENTER);
		HBox.setMargin(titleLabel, new Insets(30,0,0,0));
		titleArea.getChildren().add(titleLabel);
		
		menuButtonsArea.setAlignment(Pos.CENTER);
		VBox.setMargin(newGameBtn, new Insets(0,0,15,0));
		VBox.setMargin(loadGameBtn, new Insets(0,0,15,0));
		VBox.setMargin(controlsBtn, new Insets(0,0,15,0));
		VBox.setMargin(exitBtn, new Insets(0,0,15,0));
		menuButtonsArea.getChildren().addAll(newGameBtn, loadGameBtn, controlsBtn, exitBtn);
		
		
		// Set the holder properties.
		BorderPane holder = new BorderPane();
		holder.setTop(titleArea);
		holder.setCenter(menuButtonsArea);


		// IMPORTANT: Scene Setup
		root.getChildren().addAll(holder);
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
	}



	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	private void newGame() {
		gc.goTo(1);
 	}
	
	
	/** Sets up a dialog box for entering the save id. */
	private void loadGame() {
		Stage dialog = new Stage();
		dialog.setTitle("Load Game");
		dialog.initOwner(gc.getStage());
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initModality(Modality.WINDOW_MODAL);
		
		BorderPane dialogPane = new BorderPane();
		
		
		Label title = new Label("Enter your save ID:");
		title.setTextAlignment(TextAlignment.CENTER);
		
		TextField idField = new TextField();
		idField.setMinWidth(400);
		idField.setPromptText("Enter your save ID");
		
		
		HBox hbox = new HBox();
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction( e -> {
			dialog.close();
		});
		
		Button loadBtn = new Button("Load");
		loadBtn.setOnAction(e -> {
			Networking.loadGame(idField.getText());
			dialog.close();
		});
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(loadBtn, closeBtn);
		
		dialogPane.setTop(title);
		dialogPane.setCenter(idField);
		dialogPane.setBottom(hbox);
		Scene s = new Scene(dialogPane, 400, 100);
		dialog.setScene(s);
		dialog.show();
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
		
	}

	public void update() {
		//System.out.println("working");
	}

	public void draw() {

	}



} // End of class.