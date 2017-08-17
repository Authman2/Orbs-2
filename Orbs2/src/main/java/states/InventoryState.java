package states;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main_package.Orbs2;
import items.*;
import states.WorldState;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.stream.Collectors;

public class InventoryState extends GameState {
    
    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

    // The text area to display the inventory
    TextArea textView = new TextArea();
    
    // The static map of inventory items.
	public static ArrayList<Item> inventory = new ArrayList<Item>();



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public InventoryState(GameController gc, Stage stage) {
		super(gc, stage);
        this.handleLayout();
	}


    private void handleLayout() {
		textView.setEditable(false);
		textView.setMaxWidth(Orbs2.WIDTH);
		textView.setMaxHeight(Orbs2.HEIGHT);

		// IMPORTANT: Scene Setup
		root.getChildren().add(textView);
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
		setupKeyActions();
	}
    


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

    private void setupKeyActions() {
		scene.setOnKeyPressed(e -> {
			KeyCode w = e.getCode();
			
			switch(w) {
				case ESCAPE:
					gc.goTo(1);
					break;
			}
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
		});
	}
	

	/** Adds an item to the player's inventory. */
	public static void addToInventory(Item itm, WorldState ws) {
		inventory.add(itm);
			
		ArrayList<String> t = new ArrayList<>();
		t.add(itm.getAcquiredMessage());

		ws.getTextBox().set(t);
		ws.getTextBox().toggle();
	}
	


	/** Removes an item from the inventory. */
	public static void removeItem(String id) {
		for(Item itm : inventory) {
			if(itm.getIdentifier().equals(id)) {
				inventory.remove(itm);
				break;
			}
		}
	}




	
	

	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	/** Returns whether or not the inventory currently contains the item with a particular id. */
	public static boolean contains(String id) {
		for(Item itm : inventory) {
			if(itm.getIdentifier().equals(id)) {
				return true;
			}
		}
		return false;
	}



	/** Returns the item with the id. */
	public static Item getItem(String withID) {
		for(Item itm : inventory) {
			if(itm.getIdentifier().equals(withID)) {
				return itm;
			}
		}
		return null;
	}


	
	/** Returns an empty item based on a string. */
	public static Item fromString(String name) {
		switch(name) {
			case "ORB": return new Orb();
			case "BUCKET_OF_WATER": return new BucketOfWater();
			case "ROPE": return new Rope();
			case "COIN": return new Coin();
			case "BATCH_OF_COOKIES": return new BatchOfCookies();
			case "DOG": return new Dog();
			case "POOL_MEMBERSHIP_CARD": return new PoolMembershipCard();
			case "KEY_1": return new LabKey1();
			case "MUSEUM_CARD": return new MuseumCard();
			case "PARTY_SNACKS": return new Snacks();
			case "PICKAXE": return new Pickaxe();
			case "HATCHET": return new Hatchet();
			case "CAR_PARTS": return new CarParts();
			case "SPACE_SUIT": return new SpaceSuit();
			case "DINNER_COUPON": return new DinnerCoupon();
			case "BALES_OF_HAY": return new BalesOfHay();
			case "HOUSE_KEY": return new HouseKey();
			case "SECRET_CODE": return new SecretCode();
			case "BIRTHDAY_GIFT": return new BirthdayGift();
			case "RUBBER_BALL": return new RubberBall();
			case "ROOM_KEY": return new RoomKey();
			case "BASKET": return new Basket();
			case "APPLE": return new Apple();
			case "ORANGE": return new Orange();
			case "CORN": return new Corn();
			case "LETTUCE": return new Lettuce();
			case "TURKEY": return new Turkey();
			case "BREAD": return new Bread();
			case "YEAST": return new Yeast();
			case "BEACH_HOUSE_KEY": return new BeachHouseKey();
			default:
				return new Orb();
		}
	}






	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/
    
    public void initialize() {
		// Set the text of the tasks
		StringBuilder builder = new StringBuilder();
		builder.append("Click the 'Escape' key to go back to the game.\n\nInventory:\n\n");

		for(Item itm : inventory) {
            builder.append("---> " + itm.getName() + " ( " + itm.getQuantity() + " ) \n");
        }
		textView.setText(builder.toString());

//		// IMPORTANT: Scene Setup
//		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
	}
    
    
    public void update() {
        
    }
    
    
    public void draw() {
        
    }
    
    
    
    
}