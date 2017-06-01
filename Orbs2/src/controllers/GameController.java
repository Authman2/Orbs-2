package controllers;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main_package.Assets;
import states.ControlsState;
import states.GameState;
import states.MainMenuState;
import states.WorldState;

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
	
	
	// Game save stuff.
	public static MongoClient mongoClient = new MongoClient();
	public static MongoDatabase database = mongoClient.getDatabase("Orbs2");
	public static MongoCollection<Document> collection = database.getCollection("GameSaves");
	public static String saveID = "";
	

	
	// Used for calculating the framerate.
	final long[] frameTimes = new long[100];
    int frameTimeIndex = 0 ;
    boolean arrayFilled = false ;
    double fps = 0;


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
	            
				long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true ;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    fps = frameRate;
                }
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

	public Stage getStage() {
		return stage;
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
	
	
	/********************
	*					*
	*	    DEBUG		*
	*					*
	*********************/

	public void debug(KeyCode w) {
		if(w == KeyCode.F) {
			System.out.println("FPS: " + fps);
		}
	}
	

} // End of class.