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
import java.util.HashMap;
import tasks.*;

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

	// The speech for each npc;
	private HashMap<String, ArrayList<String>> npcSpeech;
	
	// NPC objects.
	private NPC scientist, boyRopeTask_1, boyRopeTask_2, boy_3, elderlyWomanWaterTask;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public NPCManager(WorldState ws) {
		this.worldState = ws;
		this.npcs = new ArrayList<NPC>();
		this.npcSpeech = new HashMap<String, ArrayList<String>>();

		this.loadNPCsFromFile();
		this.loadNPCSpeech();
		this.configureFinishedFunctions();
	}


	/**
	*	Here is where most of the work for this class is done. It handles loading
	* the location data for each npc and placing them in the game world.
	*/
	private void loadNPCsFromFile() {
		ArrayList<String> lines = Networking.read("maps/NPCLocations.txt");

		for(String line : lines) {
			String[] parts = line.split(" ");
        	
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
        }
	}




	/**
	*	Loads all the text that an NPC will say. In the folder resources/speech, you
	* will find separate files for each NPC's speech, where each text slide is 
	* separated by lines. There will also be different files for each npc depending
	* on whether or not certain tasks have been completed.
	*/
	private void loadNPCSpeech() {
		npcSpeech.put("scientist_1", Networking.read("speech/scientist_1.txt"));
		npcSpeech.put("scientist_2", Networking.read("speech/scientist_2.txt"));
		npcSpeech.put("elderlyWoman_1_1", Networking.read("speech/elderlyWomanWaterTask_1.txt"));
		npcSpeech.put("elderlyWoman_1_2", Networking.read("speech/elderlyWomanWaterTask_2.txt"));
		npcSpeech.put("elderlyWoman_1_3", Networking.read("speech/elderlyWomanWaterTask_3.txt"));
		npcSpeech.put("boyRopeTask_1", Networking.read("speech/boyRopeTask_1.txt"));
		npcSpeech.put("boyRopeTask_2", Networking.read("speech/boyRopeTask_2.txt"));
		npcSpeech.put("boyRopeTask_3", Networking.read("speech/boyRopeTask_3.txt"));

		scientist.setSpeech(npcSpeech.get("scientist_1"));
		elderlyWomanWaterTask.setSpeech(npcSpeech.get("elderlyWoman_1_1"));
		boyRopeTask_1.setSpeech(npcSpeech.get("boyRopeTask_1"));
		boyRopeTask_2.setSpeech(npcSpeech.get("boyRopeTask_1"));
	}





	/** Determines what to do when the player is done speaking to an npc. */
	private void configureFinishedFunctions() {
		scientist.setFinishedFunction(e -> {
			if(!TaskSystem.getTask("SPEAK_TO_SCIENTIST").isCompleted()) {
				TaskSystem.getTask("SPEAK_TO_SCIENTIST").complete();
				TaskSystem.getTask("CHARGE_ORBS").start();
				scientist.setSpeech(npcSpeech.get("scientist_2"));
			}
			return e;
		});
		elderlyWomanWaterTask.setFinishedFunction(e -> {
			if(!TaskSystem.getTask("BUCKET_OF_WATER").isStarted()) {
				TaskSystem.getTask("BUCKET_OF_WATER").start();
			}
			// else if the player has the bucket of water and the task is not completed
			// else if(!TaskSystem.getTask("BUCKET_OF_WATER").isCompleted()) {

			// }
			return e;
		});
		boyRopeTask_1.setFinishedFunction(e -> {
			if(!TaskSystem.getTask("ROPE").isStarted()) {
				TaskSystem.getTask("ROPE").start();
			}
			// else if you have a rope and the task is not completed.
			return null;
		});
		boyRopeTask_2.setFinishedFunction(e -> {
			if(!TaskSystem.getTask("ROPE").isStarted()) {
				TaskSystem.getTask("ROPE").start();
			}
			// else if you have a rope and the task is not completed.
			return null;
		});
	}





	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	public ArrayList<NPC> getNPCS() {
		return this.npcs;
	}


	public HashMap<String, ArrayList<String>> getNPCSpeech() {
		return this.npcSpeech;
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