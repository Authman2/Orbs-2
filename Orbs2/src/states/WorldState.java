package states;

import controllers.GameController;
import entities.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import je.visual.Vector2D;
import main_package.Orbs2;
import world.World;


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

        player = new Player(new Vector2D(10,8), this);

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


	public Player getPlayer() { return player; }




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
				
			player.keyActions(w);
			
			// Debugging
			if(e.isShiftDown()) { 
				currentWorld.debug(w);
				gc.debug(w);
			}
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
			player.releaseKeyActions(w);
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