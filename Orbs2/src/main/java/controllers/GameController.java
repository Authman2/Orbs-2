package controllers;

import java.util.function.Function;

import org.bson.Document;

import com.mongodb.Block;
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
	public static MongoClient mongoClient;
	public static MongoDatabase database;
	public static MongoCollection<Document> collection;
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
		this.setupMongoClient();

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


	/** Sets up the mongo db client so the user can save the game. */
	private void setupMongoClient() {
		try {
			mongoClient = new MongoClient();
		} catch(Exception err) {
			System.out.println("Couldn't load MongoDB client. The game can be played, but cannot be saved.");
		}

		database = mongoClient.getDatabase("Orbs2");
		collection = database.getCollection("GameSaves");
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
		if(w == KeyCode.M) {
			this.printDocuments(10);
		}
	}
	
	
	/** Handles printing all of the game saves from the Mongo database. */
	private void printDocuments(int limit) {
		// The print block.
		Block<Document> printBlock = new Block<Document>() {
			public void apply(Document document) {
				System.out.println(document.toJson());
			}
		};
		
		// Print each document.
		System.out.println("ALL GAME SAVES IN MONGO DATABASE:");
		collection.find().limit(limit).forEach(printBlock);
	}

} // End of class.