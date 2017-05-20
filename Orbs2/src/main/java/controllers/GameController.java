package controllers;

import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.animation.AnimationTimer;

import states.GameState;
import states.MainMenuState;
import states.WorldState;
import states.ControlsState;
import main_package.Assets;

/*
* The GameController is the main controller used to organize all other
scenes and controllers in the game. It is basically the GameStateManager.
*/
public class GameController {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// All of the game assets.
	Assets assets;

	// An array of scene controllers. Basically each game state.
	GameState[] states;

	// The current scene controller to use.
	GameState currentState;


	// The stage for the application.
	Stage stage;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public GameController(Stage stage) {
		this.stage = stage;

		// Initialize the assets.
		assets = new Assets();
		assets.initialize();

		// Setup the game states.
		states = new GameState[3];
		states[0] = new MainMenuState(this, stage);
		states[1] = new WorldState(this, stage);
		states[2] = new ControlsState(this, stage);

		currentState = states[0];

		// Start the game loop.
		new AnimationTimer() {
			public void handle(long now) {
				update();
	            draw();
			}
		}.start();
	}




	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	public void goTo(int state) {
		// HAS TO BE DONE IN THIS ORDER.
		currentState = states[state];

		initialize();

		stage.setScene(currentState.getScene());
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public GameState[] getStates() {
		return states;
	}

	public GameState getCurrentState() {
		return currentState;
	}



	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		currentState.initialize();
	}

	public void update() {
		currentState.update();
	}

	public void draw() {
		currentState.draw();
	}


} // End of class.