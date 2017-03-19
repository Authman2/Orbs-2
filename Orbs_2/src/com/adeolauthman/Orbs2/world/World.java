package com.adeolauthman.Orbs2.world;

import com.adeolauthman.Orbs2.main.Assets;
import com.adeolauthman.Orbs2.sceneControllers.GameSceneController;
import com.adeolauthman.Orbs2.sceneControllers.WorldSceneController;

import javafx.scene.image.Image;
import je.collections.ArrayConversion;
import je.files.ReadFile;

public class World {
	
	/************************
	 * 						*
	 * 		Variables		*
	 * 						*
	 ************************/
	
	WorldSceneController worldScene;
	
	int[] map;
	Tile[][] tiles;
	
	
	
	
	
	/************************
	 * 						*
	 * 		Constructor		*
	 * 						*
	 ************************/
	
	public World(String jsonName, GameSceneController ws) {
		this.worldScene = (WorldSceneController)ws;
		
		configureTileMap(jsonName);
	}

	
	/** Configures the map variable and turns it into a 2D array. */
	private void configureTileMap(String name) {
		ReadFile reader = new ReadFile("src/com/adeolauthman/Orbs2/jsons/"+name+".txt");
		try {
			String s = reader.read().replaceAll(" ", "").replaceAll("\n", "");
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
		
		for(int i = 0; i < map.length; i++) {
			
			for(int j = 0; j < map[0].length; j++) {
				
				switch(map[i][j]) {
					case 0: break;
					case 1: createTile(Assets.grass_1, i, j); break;
					case 21: createTile(Assets.tree_1_top, i, j); break;
					default: createTile(Assets.grass_1, i, j); break;
				}
			}
			
		}
		
	}
	
	
	/** Creates a Tile object. */
	private void createTile(Image img, int i, int j) {
		Tile t = new Tile(img, worldScene);
		t.setPosition(i, j);
		tiles[i][j] = t;
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
	
	
	
	
	/************************
	 * 						*
	 * 		 Abstract		*
	 * 						*
	 ************************/
	
	public void initialize() {
		
	}
	
	public void update() {
		
	}
	
	public void draw() {
		if(tiles != null) {
			for(Tile[] ts : tiles) {
				for(Tile t : ts) {
					if(t.getPosition().X < 20 && t.getPosition().Y < 20) {
						if(t.getPosition().X >= 0 && t.getPosition().Y >= 0) {
							t.draw();
						}
					}
				}
			}
		}
	}
}
