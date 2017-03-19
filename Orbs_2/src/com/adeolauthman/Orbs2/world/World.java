package com.adeolauthman.Orbs2.world;

import java.util.ArrayList;

import com.adeolauthman.Orbs2.entities.Entity;
import com.adeolauthman.Orbs2.main.Assets;
import com.adeolauthman.Orbs2.sceneControllers.GameSceneController;
import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.scene.image.Image;
import je.collections.ArrayConversion;
import je.files.ReadFile;
import je.visual.Vector2D;

public class World {
	
	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	WorldSceneController worldScene;
	
	Camera mainCamera;
	
	Vector2D position;
	
	int[] map;
	Tile[][] tiles;
	
	public float moveSpeed = 0.2f;
	
	
	
	
	/************************
	 * 						*
	 * 		Constructor		*
	 * 						*
	 ************************/
	
	public World(String jsonName, GameSceneController ws) {
		this.worldScene = (WorldSceneController)ws;
		position = new Vector2D();
		mainCamera = new Camera( this );
		
		configureTileMap(jsonName);
	}

	
	/** Configures the map variable and turns it into a 2D array. */
	private void configureTileMap(String name) {
		ReadFile reader = new ReadFile("src/com/adeolauthman/Orbs2/jsons/"+name+".txt");
		try {
			String s = reader.read().replace("\n", ",");
			String[] arr = s.split(",");
			
			this.map = new int[arr.length];
			for(int i = 0; i < arr.length; i++) { this.map[i] = Integer.parseInt(arr[i]); }
			
			int[][] map2D = ArrayConversion.OneToTwo(map, 400);
			tiles = new Tile[map2D.length][map2D[0].length];
			
			makeTiles(map2D);
			
		} catch(Exception er) { er.printStackTrace(); }
	}
	
	
	/** Creates a bunch of Tile objects based on integer values. */
	private void makeTiles(int[][] map) {
		ArrayList<Tile> ts = new ArrayList<Tile>();
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				switch(map[j][i]) {
					case 0: createTile(Assets.grass_1, i, j, ts); break;
					case 20: createTile(Assets.tree_1_top, i, j, ts); break;
					case 40: createTile(Assets.tree_1_bottom, i, j, ts); break;
					default: createTile(Assets.grass_1, i, j, ts); break;
				}
			}
		}
		
		
		Entity[] ents = new Entity[ts.size()];
		for(int i = 0; i < ents.length; i++) { ents[i] = ts.get(i); }
		mainCamera.addChildren(ents);
	}
	
	
	/** Creates a Tile object. */
	private void createTile(Image img, int i, int j, ArrayList<Tile> ts) {
		Tile t = new Tile(img, worldScene);
		t.setPosition(i + position.X, j + position.Y);
		tiles[i][j] = t;
		ts.add(t);
	}
	
	
	
	/************************
	 * 						*
	 * 		 Setters		*
	 * 						*
	 ************************/
	
	
	
	/************************
	 * 						*
	 * 		 Getters		*
	 * 						*
	 ************************/
	
	public Camera getMainCamera() { return mainCamera; }
	
	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		mainCamera.initialize();
	}
	
	public void update() {
		mainCamera.update();
	}
	
	public void draw() {		
		if(tiles != null) {
			
		}
		
		mainCamera.draw();
	}
	
	
}
