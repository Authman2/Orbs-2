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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.beans.property.DoubleProperty;

import je.visual.Vector2D;

import main_package.Orbs2;
import controllers.GameController;
import world.World;
import entities.Player;
import main_package.Assets;

public class WorldState extends GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	Canvas canvas;
	GraphicsContext graphics;

    // The array of worlds (rooms, caves, etc.) that the player can go into.
    World[] worlds;

    // The world that the player is currently in.
    World currentWorld;

    // The player.
    Player player;


	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public WorldState(GameController gc, Stage stage) {
		super(gc, stage);

		canvas = new Canvas(Orbs2.WIDTH, Orbs2.HEIGHT);
		graphics = canvas.getGraphicsContext2D();

        player = new Player(new Vector2D(10,7), this);

		setupWorlds();
	}


	private void setupWorlds() {
		// World Setup
        worlds = new World[5];
        worlds[0] = new World(this.player, this, "World", 400);
        // worlds[1] = new World(this, "");
        // worlds[2] = new World(this, "");
        // worlds[3] = new World(this, "");
        // worlds[4] = new World(this, "");

        currentWorld = worlds[0];
	}




	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	private void clear() {
		graphics.clearRect(0,0,Orbs2.WIDTH,Orbs2.HEIGHT);
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public World getCurrentWorld() {
		return currentWorld;
	}


	public GraphicsContext getGraphics() {
		return graphics;
	}


	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		if(currentWorld != null) currentWorld.initialize();


		// IMPORTANT: Scene Setup
		root.getChildren().add(canvas);
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);


		// Key Input
		scene.setOnKeyPressed(e -> {
			KeyCode w = e.getCode();
			currentWorld.keyActions(w);
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
			currentWorld.releaseKeyActions(w);
		});
	}

	public void update() {
		if(currentWorld != null) currentWorld.update();
	}

	public void draw() {
		clear();

		if(currentWorld != null) currentWorld.draw();
	}



} // End of class.