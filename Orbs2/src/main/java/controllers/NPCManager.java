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
	private NPC scientist, boyRopeTask_1, boyRopeTask_2, boy_3, elderlyWomanWaterTask, boy_4, boy_5, girl_1,
	girl_2, ropeTaskStoreOwner, 

	girl_3, women_1, man_1, man_2, boy_6, man_3, women_2, girl_4, boy_7, boy_8, girl_5, boy_9, boy_10, boy_11;



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
            if(parts.length < 7) continue;
        	
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
		npcSpeech.put("boy_3", Networking.read("speech/boy_3.txt"));
		npcSpeech.put("boy_4_5", Networking.read("speech/boy_4_5.txt"));
		npcSpeech.put("girl_1", Networking.read("speech/girl_1.txt"));
		npcSpeech.put("girl_2", Networking.read("speech/girl_2.txt"));
		npcSpeech.put("ropeTaskStoreOwner_0", Networking.read("speech/ropeTaskStoreOwner_0.txt"));
		npcSpeech.put("ropeTaskStoreOwner_1", Networking.read("speech/ropeTaskStoreOwner_1.txt"));
		npcSpeech.put("ropeTaskStoreOwner_2", Networking.read("speech/ropeTaskStoreOwner_2.txt"));
		npcSpeech.put("ropeTaskStoreOwner_3", Networking.read("speech/ropeTaskStoreOwner_3.txt"));
        npcSpeech.put("girl_3", Networking.read("speech/girl_3.txt"));
        npcSpeech.put("women_1", Networking.read("speech/women_1.txt"));
        npcSpeech.put("man_1", Networking.read("speech/man_1.txt"));
        npcSpeech.put("man_2", Networking.read("speech/man_2.txt"));
        npcSpeech.put("boy_6", Networking.read("speech/boy_6.txt"));
        npcSpeech.put("man_3", Networking.read("speech/man_3.txt"));
        npcSpeech.put("women_2", Networking.read("speech/women_2.txt"));
        npcSpeech.put("girl_4", Networking.read("speech/girl_4.txt"));
        npcSpeech.put("boy_7", Networking.read("speech/boy_7.txt"));
        npcSpeech.put("boy_8", Networking.read("speech/boy_8.txt"));
        npcSpeech.put("girl_5", Networking.read("speech/girl_5.txt"));
        npcSpeech.put("boy_9", Networking.read("speech/boy_9.txt"));
        npcSpeech.put("boy_10", Networking.read("speech/boy_10.txt"));
        npcSpeech.put("boy_11", Networking.read("speech/boy_11.txt"));
        

		scientist.setSpeech(npcSpeech.get("scientist_1"));
		elderlyWomanWaterTask.setSpeech(npcSpeech.get("elderlyWoman_1_1"));
		boyRopeTask_1.setSpeech(npcSpeech.get("boyRopeTask_1"));
		boyRopeTask_2.setSpeech(npcSpeech.get("boyRopeTask_1"));
		boy_3.setSpeech(npcSpeech.get("boy_3"));
		boy_4.setSpeech(npcSpeech.get("boy_4_5"));
		boy_5.setSpeech(npcSpeech.get("boy_4_5"));
		girl_1.setSpeech(npcSpeech.get("girl_1"));
		girl_2.setSpeech(npcSpeech.get("girl_2"));
		ropeTaskStoreOwner.setSpeech(npcSpeech.get("ropeTaskStoreOwner_0"));
        girl_3.setSpeech(npcSpeech.get("girl_3"));
        women_1.setSpeech(npcSpeech.get("women_1"));
        man_1.setSpeech(npcSpeech.get("man_1"));
        man_2.setSpeech(npcSpeech.get("man_2"));
        boy_6.setSpeech(npcSpeech.get("boy_6"));
        man_3.setSpeech(npcSpeech.get("man_3"));
        women_2.setSpeech(npcSpeech.get("women_2"));
        girl_4.setSpeech(npcSpeech.get("girl_4"));
        boy_7.setSpeech(npcSpeech.get("boy_7"));
        boy_8.setSpeech(npcSpeech.get("boy_8"));
        girl_5.setSpeech(npcSpeech.get("girl_5"));
        boy_9.setSpeech(npcSpeech.get("boy_9"));
        boy_10.setSpeech(npcSpeech.get("boy_10"));
        boy_11.setSpeech(npcSpeech.get("boy_11"));
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
				ropeTaskStoreOwner.setSpeech(npcSpeech.get("ropeTaskStoreOwner_1"));
			}
			// else if you have a rope and the task is not completed.
			return null;
		});
		boyRopeTask_2.setFinishedFunction(e -> {
			if(!TaskSystem.getTask("ROPE").isStarted()) {
				TaskSystem.getTask("ROPE").start();
				ropeTaskStoreOwner.setSpeech(npcSpeech.get("ropeTaskStoreOwner_1"));
			}
			// else if you have a rope and the task is not completed.
			return null;
		});
		ropeTaskStoreOwner.setFinishedFunction(e -> {
			// if the user has started the rope task and has enough coins or doesn't, change text.
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