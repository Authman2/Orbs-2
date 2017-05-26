package world;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import java.util.*;

import je.files.ReadFile;
import je.collections.ArrayConversion;
import je.visual.Vector2D;

import states.WorldState;
import main_package.Assets;
import entities.*;

public class World extends Entity {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// A reference to the world state.
   	WorldState worldState;

   	// The name of the file containing the map data.
   	String mapName;

   	// The size of the map
   	int mapSize;

   	// The matrix of tiles.
   	ArrayList<Tile> tiles;

   	// Whether or not the tiles are moving.
   	boolean moving;


   	// A reference to the player that is currently in this world.
   	Player player;

   	// All of the entities in the game world.
   	ArrayList<Entity> entities;




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public World(Player player, WorldState ws, String mapName, int size) {
		super(new Vector2D(), ws);
		this.worldState = ws;
		this.mapName = mapName;
		this.mapSize = size;
		this.moving = false;
		this.player = player;
		this.entities = new ArrayList<Entity>();

		this.configureMap();
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	// Creates the matrix of id's for the map.
	private void configureMap() {
		ReadFile reader = new ReadFile("/Users/adeolauthman/Documents/AdeolasCodingStuff/JavaPrograms/Orbs2/resources/maps/"+this.mapName+".txt");
		try {
			String s = reader.read();
			String[] arr = s.split("-");
			
			String[][] map2D = (String[][])ArrayConversion.OneToTwo(arr, this.mapSize);

			// Start making the tiles and the map.
			this.tiles = new ArrayList<Tile>();
			this.makeMap(map2D);

		} catch(Exception er) { er.printStackTrace(); }
	}
	

	// Handles actually making the tile map.
	private void makeMap(String[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				
				switch(map[i][j]) {
					case "0": this.createTile(j, i, Assets.GRASS_1); break;
					case "20": this.createTile(j, i, Assets.TREE_TOP); break;
					case "40": this.createTile(j, i, Assets.TREE_BOTTOM); break;
					default: this.createTile(j, i, Assets.GRASS_1); break;
				}

			}
		}
	}


	// Creates a tile.
	private void createTile(int i, int j, Image image) {
		Tile tile = new Tile(new Vector2D(i,j), image, this.worldState);
		tiles.add(tile);
	}


	// Handles the key actions
	public void keyActions(KeyCode code) {
		if(code == KeyCode.UP) {
			player.changeDirection(Direction.UP);
			this.tiles.forEach(e -> { e.yVel = this.speed; });
		}
		if(code == KeyCode.DOWN) {
			player.changeDirection(Direction.DOWN);
			this.tiles.forEach(e -> { e.yVel = -this.speed; });
		}
		if(code == KeyCode.LEFT) {
			player.changeDirection(Direction.LEFT);
			this.tiles.forEach(e -> { e.xVel = this.speed; });
		}
		if(code == KeyCode.RIGHT) {
			player.changeDirection(Direction.RIGHT);
			this.tiles.forEach(e -> { e.xVel = -this.speed; });
		}
	}


	// Handles key releases
	public void releaseKeyActions(KeyCode code) {
		if(code == KeyCode.UP) {
			this.tiles.forEach(e -> { e.yVel = 0; });
		}
		if(code == KeyCode.DOWN) {
			this.tiles.forEach(e -> { e.yVel = 0; });
		}
		if(code == KeyCode.LEFT) {
			this.tiles.forEach(e -> { e.xVel = 0; });
		}
		if(code == KeyCode.RIGHT) {
			this.tiles.forEach(e -> { e.xVel = 0; });
		}
	}


	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public Vector2D getPosition() {
		return position;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public boolean isMoving() {
		return moving;
	}


	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		if(player != null) { player.initialize(); }
		if(tiles != null) {
			this.tiles.forEach(e -> { e.initialize(); });
		}
	}

	public void update() {
		if(player != null) { player.update(); }
		if(tiles != null) {
			this.tiles.forEach(e -> { e.update(); });

			if(this.tiles.get(0).xVel == 0 && this.tiles.get(0).yVel == 0) {
				this.moving = false;
			} else {
				this.moving = true;
			}
		}
	}

	public void draw() {
		if(tiles != null) {
			this.tiles.forEach(e -> { e.draw(); });
		}
		if(player != null) { player.draw(); }
	}

} // End of class.