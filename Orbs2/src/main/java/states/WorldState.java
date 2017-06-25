package states;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import controllers.GameController;
import entities.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import je.visual.Vector2D;
import main_package.Orbs2;
import world.World;
import hud.*;


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
    
    // The menu that lets the player do different things in the game.
    Menu menu;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public WorldState(GameController gc, Stage stage) {
		super(gc, stage);
		canvas = new Canvas(Orbs2.WIDTH, Orbs2.HEIGHT);
		graphics = canvas.getGraphicsContext2D();


		menu = new Menu(gc, graphics);
        player = new Player(new Vector2D(10,8), this);

		setupWorlds();


		// IMPORTANT: Scene Setup
		root.getChildren().add(canvas);
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
		handleKeyActions();
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

	
	/** Handles saving the player data from the game. */
	public void saveGame() {
		// Check if the global variable for id is empty. If so, save a new file, otherise update.
		// If there is already a save loaded, then just update.
		if(GameController.saveID != "") {
			
			// Get the document that needs to be updated.
			Document updating = GameController.collection.find(Filters.eq("_id",new ObjectId(GameController.saveID))).first();
			
			// Update the document.
			GameController.collection.updateOne(
					updating, 
					Updates.combine( Updates.set("positionX", player.getPosition().X),
									 Updates.set("positionY", player.getPosition().Y) )
					);
			
			// Tell the user that the game was saved.
			this.showIDDialog("Saved the game! Here is your save ID, keep it somewhere safe so you "
							+ "can return to the same place next time you play.", 
							  GameController.saveID);
		} else {
			
			// Otherwise, save a new file.
			Document data = new Document("positionX",player.getPosition().X)
								 .append("positionY",player.getPosition().Y);
			GameController.collection.insertOne(data);
			
			// Set the id for the entire game.
			ObjectId o = data.getObjectId("_id");
			GameController.saveID = o.toString();
			
			
			// Tell the user their save id.
			this.showIDDialog("Saved the game! Here is your save ID, keep it somewhere safe so you "
							+ "can return to the same place next time you play.", 
							o.toString());
		}
	}
	
	
	/** Handles loading the game data from a string id. Takes all the data from the save document
	 * and puts it into the game. */
	public void loadGame(Document data) {
		Double savedPosX = Double.parseDouble(data.get("positionX").toString());
		Double savedPosY = Double.parseDouble(data.get("positionY").toString());
		
		
		// Set player/world values based on save data.
		player.getPosition().X = savedPosX.floatValue();
		player.getPosition().Y = savedPosY.floatValue();
	}
	
	
	/** Just a dialog box for showing the user their save id. */
	private void showIDDialog(String t, String message) {
		Stage dialog = new Stage();
		dialog.setTitle("Save ID");
		dialog.initOwner(gc.getStage());
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initModality(Modality.WINDOW_MODAL);
		
		BorderPane dialogPane = new BorderPane();
		
		
		Label title = new Label(t);
		title.setWrapText(true);
		title.setTextAlignment(TextAlignment.CENTER);
		
		TextField idShower = new TextField(message);
		idShower.setEditable(false);
		idShower.setAlignment(Pos.CENTER);
		
		dialogPane.setTop(title);
		dialogPane.setCenter(idShower);
		Scene s = new Scene(dialogPane, 400, 100);
		dialog.setScene(s);
		dialog.show();
	}
	
	
	private void handleKeyActions() {
		// Key Input
		scene.setOnKeyPressed(e -> {
			KeyCode w = e.getCode();
				
			player.keyActions(w);
			
			// Debugging
			if(e.isShiftDown()) { 
				currentWorld.debug(w);
				gc.debug(w);
			}

			// View Tasks
			if(w == KeyCode.M) {
				this.menu.toggle();
			}
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
			player.releaseKeyActions(w);
		});

		scene.setOnMouseClicked(e -> {
			menu.mouseClickEvents(e);
		});
		scene.setOnMouseMoved(e -> {
			menu.mouseMoveEvents(e);
		});
		scene.setOnMousePressed(e -> {
			menu.mousePressedEvents(e);
		});
		scene.setOnMouseReleased(e -> {
			menu.mouseReleasedEvents(e);
		});
	}
	
	

	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public World getCurrentWorld() { return currentWorld; }


	public GraphicsContext getGraphics() { return graphics; }


	public Player getPlayer() { return player; }


	public Menu getMenu() { return menu; }



	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		if(currentWorld != null) currentWorld.initialize();
		if(menu != null) menu.initialize();
		
	}

	public void update() {
		if(menu != null) {
			if(menu.isOpen()) {
				menu.update();
			} else {
				if(currentWorld != null) currentWorld.update();
			}
		}
	}

	public void draw() {
		clear();

		if(currentWorld != null) currentWorld.draw();
		if(menu != null) menu.draw();
	}

} // End of class.