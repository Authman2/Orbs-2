package CompletionHandlers;

import states.*;
import kotlin.jvm.functions.Function0;
import kotlin.Unit;

import tasks.*;
import items.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import controllers.*;
import hud.*;
import entities.*;
import minigames.*;

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
    public Function0 speakToScientistHandler = new Function0() {
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
    public Function0 elderlyWomanWaterTaskHandler = new Function0() {
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
    public Function0 boyRopeTaskHandler = new Function0() {
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
    public Function0 ropeTaskStoreOwnerHandler = new Function0() {
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
    public Function0 coinManHandler = new Function0() {
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
    public Function0 lostDogHandler = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("MISSING_DOG").isStarted()) {
                NPCManager.dogOwnerWoman.setSpeech(NPCManager.npcSpeech.get("dogOwnerWoman_2"));
                
                InventoryState.addToInventory(new Dog(), CompletionHandlers.worldState);
                NPCManager.npcs.remove(NPCManager.lostDog);
                TextBox.open = false;
            }
            return null;
        }
    };
    public Function0 dogOwnerWomanHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("MISSING_DOG").isStarted()) {
                TaskSystem.getTask("MISSING_DOG").start();
                NPCManager.lostDog.setSpeech(NPCManager.npcSpeech.get("lostDog_2"));
            } else {
                if(!TaskSystem.getTask("MISSING_DOG").isCompleted() && InventoryState.contains("DOG")) {
                    InventoryState.addToInventory(new PoolMembershipCard(), CompletionHandlers.worldState);
                    InventoryState.removeItem("DOG");
                    
                    NPCManager.dogOwnerWoman.setSpeech(NPCManager.npcSpeech.get("dogOwnerWoman_3"));
                    TaskSystem.getTask("MISSING_DOG").complete();
                    NPCManager.swimmingLessonsWoman.setSpeech(NPCManager.npcSpeech.get("swimmingLessonsWoman_2"));
                }
            }
            return null;
        }
    };
    public Function0 swimmingLessonsWomanHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("SWIMMING_LESSONS").isStarted()) {
                TaskSystem.getTask("SWIMMING_LESSONS").start();
            }
            if(SwimmingMG.swimmingTaskCompleted == true) {
                NPCManager.swimmingLessonsWoman.setSpeech(NPCManager.npcSpeech.get("swimmingLessonsWoman_4"));
                TaskSystem.getTask("SWIMMING_LESSONS").complete();

                Player.canSwim = true;
                CompletionHandlers.worldState.getCurrentWorld().getTilesL1().stream().forEach(it -> {
                    if(it.getClass().getName().contains("WATER")) {
                        it.setSolid(false);
                    }
                });
                CompletionHandlers.worldState.getCurrentWorld().getTilesL2().stream().forEach(it -> {
                    if(it.getClass().getName().contains("WATER")) {
                        it.setSolid(false);
                    }
                });
            }
            else if(SwimmingMG.swimmingTaskCompleted == false) {
                CompletionHandlers.worldState.gc.goTo(5);
            }
            
            return null;
        }
    };
    public Function0 birthdaySnacksMomHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("COOKIES").isStarted()) {
                TaskSystem.getTask("COOKIES").start();

                if(InventoryState.contains("BATCH_OF_COOKIES")) {
                    NPCManager.birthdaySnacksMom.setSpeech(NPCManager.npcSpeech.get("birthdaySnacksMom_2"));
                }
            }
            else {
                if(InventoryState.contains("BATCH_OF_COOKIES")) {
                    InventoryState.addToInventory(new MuseumCard(), CompletionHandlers.worldState);
                    InventoryState.removeItem("BATCH_OF_COOKIES");
                    NPCManager.birthdaySnacksMom.setSpeech(NPCManager.npcSpeech.get("birthdaySnacksMom_3"));

                    TaskSystem.getTask("COOKIES").complete();
                }
            }
            return null;
        }
    };
    public Function0 electricityManHandler = new Function0() {
        public Unit invoke() {
            if(!TaskSystem.getTask("POWER_PLANT").isStarted()) {
                TaskSystem.getTask("POWER_PLANT").start();
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
    public static Function0 lostDogHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("MISSING_DOG").isStarted()) {
                NPCManager.lostDog.setSpeech(NPCManager.npcSpeech.get("lostDog_2"));
            }
            if(InventoryState.contains("DOG") || TaskSystem.getTask("MISSING_DOG").isCompleted()) {
                NPCManager.npcs.remove(NPCManager.lostDog);
            }
            return null;
        }
    };
    public static Function0 dogOwnerWomanHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("MISSING_DOG").isCompleted()) {
                NPCManager.dogOwnerWoman.setSpeech(NPCManager.npcSpeech.get("dogOwnerWoman_3"));
            }
            else if(TaskSystem.getTask("MISSING_DOG").isStarted()) {
                if(InventoryState.contains("DOG")) {
                    NPCManager.dogOwnerWoman.setSpeech(NPCManager.npcSpeech.get("dogOwnerWoman_2"));
                }
            }
            return null;
        }
    };
    public static Function0 swimmingLessonsWomanHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("SWIMMING_LESSONS").isCompleted()) {
                NPCManager.swimmingLessonsWoman.setSpeech(NPCManager.npcSpeech.get("swimmingLessonsWoman_4"));
                
                // Make sure the player can swim.
                SwimmingMG.swimmingTaskCompleted = true;
                Player.canSwim = true;
                CompletionHandlers.worldState.getCurrentWorld().getTilesL1().stream().forEach(it -> {
                    if(it.getClass().getName().contains("WATER")) {
                        it.setSolid(false);
                    }
                });
                CompletionHandlers.worldState.getCurrentWorld().getTilesL2().stream().forEach(it -> {
                    if(it.getClass().getName().contains("WATER")) {
                        it.setSolid(false);
                    }
                });
            }
            else if(InventoryState.contains("POOL_MEMBERSHIP_CARD")) {
                NPCManager.swimmingLessonsWoman.setSpeech(NPCManager.npcSpeech.get("swimmingLessonsWoman_2"));
            }
            return null;
        }
    };
    public static Function0 birthdaySnacksMomHandler_Loaded = new Function0() {
        public Unit invoke() {
            if(TaskSystem.getTask("COOKIES").isCompleted()) {
                NPCManager.birthdaySnacksMom.setSpeech(NPCManager.npcSpeech.get("birthdaySnacksMom_3"));
            } 
            else if(TaskSystem.getTask("COOKIES").isStarted()) {
                if(InventoryState.contains("BATCH_OF_COOKIES")) {
                    NPCManager.birthdaySnacksMom.setSpeech(NPCManager.npcSpeech.get("birthdaySnacksMom_2"));
                }
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