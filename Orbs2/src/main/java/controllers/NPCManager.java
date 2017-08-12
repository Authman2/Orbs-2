package controllers;

import entities.NPC;
import java.util.ArrayList;
import javafx.scene.image.Image;
import main_package.*;
import states.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import je.visual.Vector2D;

public class NPCManager {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// A reference to the world state.
	WorldState worldState;

	// All the npcs.
	private ArrayList<NPC> npcs;
	
	// NPC objects.
	private NPC scientist;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public NPCManager(WorldState ws) {
		this.worldState = ws;
		this.npcs = new ArrayList<NPC>();

		this.loadNPCsFromFile();
	}


	/**
	*	Here is where most of the work for this class is done. It handles loading
	* the location data for each npc and placing them in the game world.
	*/
	private void loadNPCsFromFile() {
		InputStream npcLocs = getClass().getResourceAsStream("/resources/maps/NPCLocations.txt");
		BufferedReader reader = null;

		// Load the text file.
		try {
			reader = new BufferedReader(new InputStreamReader(npcLocs));

			String str = reader.readLine();
			while(str != null) {
				String[] parts = str.split(" ");
        	
	        	String name = parts[0];
	        	int x = Integer.parseInt(parts[1]);
	        	int y = Integer.parseInt(parts[2]);
	        	Image upSprite = worldState.gc.getAssets().fromString(parts[3]);
	        	Image downSprite = worldState.gc.getAssets().fromString(parts[4]);
	        	Image leftSprite = worldState.gc.getAssets().fromString(parts[5]);
	        	Image rightSprite = worldState.gc.getAssets().fromString(parts[6]);

	        	// Create the NPC.
	        	try {
	        		Field e = NPCManager.class.getDeclaredField(name);
	        		e.setAccessible(true);

	        		NPC val = new NPC(new Vector2D(x,y), this.worldState);
	        		val.setName(name);
	        		val.setSprites( new Image[] {upSprite, downSprite, leftSprite, rightSprite});
					e.set(this, val);

					try {
						NPC gotten = (NPC)e.get(this);
						this.npcs.add(gotten);
					} catch(Exception e3) {

					}

	        	} catch(Exception err) {
	        		err.printStackTrace();
	        	}

				// Restart the loop.
				try { str = reader.readLine(); } catch(Exception err) { err.printStackTrace(); break; }
			}
			reader.close();
		} catch(Exception err) {
			err.printStackTrace();
		}
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public ArrayList<NPC> getNPCS() {
		return this.npcs;
	}




	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/



	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {

	}


	public void update() {
		for(NPC npc : this.npcs) {
			npc.update();
		}
	}


	public void draw() {
		for(NPC npc : this.npcs) {
			npc.draw();
		}
	}


}