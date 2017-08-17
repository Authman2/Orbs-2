package CompletionHandlers;

import states.*;
import kotlin.jvm.functions.Function0;
import kotlin.Unit;

import tasks.*;
import items.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import controllers.*;

public class CompletionHandlers {

    /********************
	*					*
	*     VARIABLES	    *
	*					*
	*********************/

    //-KrhvqCIGS_2i3CbmWT7
    public static WorldState worldState;
    public static ArrayList<Function0> onLoadHandlers = new ArrayList<Function0>();


    // Handlers
    public static Function0 speakToScientistHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("SPEAK_TO_SCIENTIST").isCompleted()) {
				TaskSystem.getTask("SPEAK_TO_SCIENTIST").complete();
				TaskSystem.getTask("CHARGE_ORBS").start();
				NPCManager.scientist.setSpeech(NPCManager.npcSpeech.get("scientist_2"));

				Orb item = new Orb();
				item.setQuantity(10); item.setAcquiredMessage();
				InventoryState.addToInventory(item, CompletionHandlers.worldState);
			}
            return null;
        }
    };
    public static Function0 elderlyWomanWaterTaskHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("BUCKET_OF_WATER").isStarted()) {
				TaskSystem.getTask("BUCKET_OF_WATER").start();
			}
			else if(!TaskSystem.getTask("BUCKET_OF_WATER").isCompleted() && InventoryState.contains("BUCKET_OF_WATER")) {
				InventoryState.removeItem("BUCKET_OF_WATER");
				InventoryState.addToInventory(new BatchOfCookies(), worldState);
				TaskSystem.getTask("BUCKET_OF_WATER").complete();
				NPCManager.elderlyWomanWaterTask.setSpeech(NPCManager.npcSpeech.get("elderlyWoman_1_3"));
			}
            return null;
        }
    };
    public static Function0 boyRopeTaskHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("ROPE").isStarted()) {
				TaskSystem.getTask("ROPE").start();
				NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_1"));
			}
			else if(InventoryState.contains("ROPE")) {
				InventoryState.addToInventory(new BucketOfWater(), CompletionHandlers.worldState);
				InventoryState.removeItem("ROPE");
				TaskSystem.getTask("ROPE").complete();
				NPCManager.boyRopeTask_1.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_3"));
				NPCManager.boyRopeTask_2.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_3"));
				NPCManager.elderlyWomanWaterTask.setSpeech(NPCManager.npcSpeech.get("elderlyWoman_1_2"));
			}
            return null;
        }
    };
    public static Function0 ropeTaskStoreOwnerHandler = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("ROPE").isStarted() && InventoryState.contains("COIN")) {
				if(InventoryState.getItem("COIN").getQuantity() == 20) {
					InventoryState.addToInventory(new Rope(), CompletionHandlers.worldState);
					InventoryState.removeItem("COIN");
					NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_3"));
					NPCManager.boyRopeTask_1.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_2"));
					NPCManager.boyRopeTask_2.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_2"));
				}
			}
            return null;
        }
    };
    public static Function0 coinManHandler = new Function0() {
        public Unit invoke() {
            if(!InventoryState.contains("COIN") && !TaskSystem.getTask("ROPE").isCompleted() && !InventoryState.contains("ROPE")) {
				Coin c = new Coin();
				c.setQuantity(20); c.setAcquiredMessage();
				InventoryState.addToInventory(c, CompletionHandlers.worldState);
				NPCManager.coinMan.setSpeech(NPCManager.npcSpeech.get("coinMan_2"));
				NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_2"));
			}
            return null;
        }   
    };
    

    // Load Game Handlers
    public static Function0 speakToScientistHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("SPEAK_TO_SCIENTIST").isCompleted()) {
				TaskSystem.getTask("CHARGE_ORBS").start();
				NPCManager.scientist.setSpeech(NPCManager.npcSpeech.get("scientist_2"));
            }
            return null;
        }
    };
    public static Function0 elderlyWomanWaterTaskHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("BUCKET_OF_WATER").isStarted()) {
                if(TaskSystem.getTask("BUCKET_OF_WATER").isCompleted()) {
                    InventoryState.removeItem("BUCKET_OF_WATER");
                    TaskSystem.getTask("BUCKET_OF_WATER").complete();
                    NPCManager.elderlyWomanWaterTask.setSpeech(NPCManager.npcSpeech.get("elderlyWoman_1_3"));
                } else {
                    if(InventoryState.contains("BUCKET_OF_WATER")) {
                        NPCManager.elderlyWomanWaterTask.setSpeech(NPCManager.npcSpeech.get("elderlyWoman_1_2"));
                    }
                }
                
            }
            return null;
        }
    };
    public static Function0 boyRopeTaskHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("ROPE").isCompleted()) {
                NPCManager.boyRopeTask_1.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_3"));
                NPCManager.boyRopeTask_2.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_3"));
            }
            else if(TaskSystem.getTask("ROPE").isStarted()) {
                if(InventoryState.contains("ROPE")) {
                    NPCManager.boyRopeTask_1.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_2"));
                    NPCManager.boyRopeTask_2.setSpeech(NPCManager.npcSpeech.get("boyRopeTask_2"));
                }
            }
            return null;
        }
    };
    public static Function0 ropeTaskStoreOwnerHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("ROPE").isCompleted()) {
                NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_3"));
            }
            else if(TaskSystem.getTask("ROPE").isStarted()) {
                if(InventoryState.contains("ROPE")) {
                    NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_3"));
                }
                else if(InventoryState.contains("COIN") && InventoryState.getItem("COIN").getQuantity() == 20) {
                    NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_2"));
                } 
                else {
                    NPCManager.ropeTaskStoreOwner.setSpeech(NPCManager.npcSpeech.get("ropeTaskStoreOwner_1"));
                }
            }
            return null;
        }
    };
    public static Function0 coinManHandler_Loaded = new Function0() {
        public Unit invoke() {
            if((InventoryState.contains("COIN") && InventoryState.getItem("COIN").getQuantity() == 20)
                || TaskSystem.getTask("ROPE").isCompleted() || InventoryState.contains("ROPE")) {
                NPCManager.coinMan.setSpeech(NPCManager.npcSpeech.get("coinMan_2"));
            }
            return null;
        }
    };






    
    /********************
	*					*
	*     VARIABLES	    *
	*					*
    *********************/
    
    public CompletionHandlers(WorldState ws) {
        this.worldState = ws;

        // Add all of the load handlers to the list.
        for(Field f : CompletionHandlers.class.getFields()) {
            if(f.getName().contains("_Loaded")) {
                try {
                    Function0 gotten = (Function0)f.get(this);
                    onLoadHandlers.add(gotten);
                } catch(Exception err) {
                    err.printStackTrace();
                }
            }
        }
    }


}