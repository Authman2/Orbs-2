package states;

import controllers.*;
import entities.*;
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
import java.util.Map;
import java.util.HashMap;
import javafx.util.Pair;
import java.util.function.Function;


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
    Menu menu, npcMenu;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public WorldState(GameController gc, Stage stage) {
		super(gc, stage);
		canvas = new Canvas(Orbs2.WIDTH, Orbs2.HEIGHT);
		graphics = canvas.getGraphicsContext2D();


		setupMenu();
        player = new Player(new Vector2D(14,16), this);

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


	private void setupMenu() {
		Pair[] items = new Pair[4];
		items[0] = new Pair<String, Function<?,?>>("View Tasks", e -> { 
			gc.goTo(3); 
			return null;
		});
		items[1] = new Pair<String, Function<?,?>>("Inventory", e -> { 
			System.out.println("Inventory");
			return null;
		});
		items[2] = new Pair<String, Function<?,?>>("Save Game", e -> { 
			saveGame();
			return null;
		});
		items[3] = new Pair<String, Function<?,?>>("Close", e -> { 
			menu.toggle();
			return null;
		});
		menu = new Menu(gc, graphics, items);


		// Setup the NPC dialog.
        Pair[] items2 = new Pair[2];
		items2[0] = new Pair<String, Function<?,?>>("Speak to ", e -> { 
			 
			return null;
		});
		items2[1] = new Pair<String, Function<?,?>>("Close", e -> { 
			npcMenu.toggle();
			return null;
		});
		npcMenu = new Menu(gc, graphics, items2);
	}


	public void toggleNPCMenu(NPC npc) {
		npcMenu.setMenuItem(0, new Pair<String, Function<?,?>>("Speak to " + npc.getName(), e -> { 

			return null;
		}));
		npcMenu.toggle();
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
		Map<String, Object> saveData = new HashMap<String, Object>();
		saveData.put("positionX", player.getPosition().X);
		saveData.put("positionY", player.getPosition().Y);

		Networking.saveGame(saveData, 
							success -> { System.out.println("Saved!"); return null; },
							failure -> { System.out.println("Problem saving game: " + failure); return null; }) ;
	}
	
	
	/** Handles loading the game data from a string id. Takes all the data from the save document
	 * and puts it into the game. */
	public void loadGame(String id) {
		Networking.loadGame(id);
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

			// Open Menu
			if(w == KeyCode.M) {
				if(!npcMenu.isOpen()) {
					this.menu.toggle();
				}
			}
			// Open NPC Menu
			if(w == KeyCode.C) {
				if(!menu.isOpen()) {
					currentWorld.getNPCManager().getNPCS().stream().forEach(npc -> {
						if(npc.nextTo(player)) {
							this.toggleNPCMenu(npc);
						}
					});
				}
			}
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
			player.releaseKeyActions(w);
		});

		scene.setOnMouseClicked(e -> {
			menu.mouseClickEvents(e);
			npcMenu.mouseClickEvents(e);
		});
		scene.setOnMouseMoved(e -> {
			menu.mouseMoveEvents(e);
			npcMenu.mouseMoveEvents(e);
		});
		scene.setOnMousePressed(e -> {
			menu.mousePressedEvents(e);
			npcMenu.mousePressedEvents(e);
		});
		scene.setOnMouseReleased(e -> {
			menu.mouseReleasedEvents(e);
			npcMenu.mouseReleasedEvents(e);
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
		if(npcMenu != null) npcMenu.initialize();
	}

	public void update() {
		if(menu != null) {
			if(menu.isOpen()) { menu.update(); } 
			else if(npcMenu != null && npcMenu.isOpen()) {
				npcMenu.update();
			} else {
				if(currentWorld != null) currentWorld.update();
			}
		}
	}

	public void draw() {
		clear();

		if(currentWorld != null) currentWorld.draw();
		if(menu != null) menu.draw();
		if(npcMenu != null) {
			if(npcMenu.isOpen()) {
				npcMenu.draw();
			}
		}
	}

} // End of class.