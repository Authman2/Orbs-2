package main_package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import java.io.*;
import java.io.File;
import java.net.URL;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.lang.reflect.Field;

public class Assets {

	// Spritesheets
	BufferedImage spritesheet, playerSpritesheet;
	
	
	// Sprites
	public static Image PLAYER_UP, PLAYER_DOWN, PLAYER_LEFT, PLAYER_RIGHT;
	public static Image PLAYER_WALK_UP_1, PLAYER_WALK_UP_2,
						PLAYER_WALK_DOWN_1, PLAYER_WALK_DOWN_2,
						PLAYER_WALK_LEFT_1, PLAYER_WALK_LEFT_2,
						PLAYER_WALK_RIGHT_1, PLAYER_WALK_RIGHT_2;
	public static Image SCIENTIST_UP, SCIENTIST_DOWN, SCIENTIST_LEFT, SCIENTIST_RIGHT;
	
	public static Image EMPTY, GRASS_1, TREE_TOP, TREE_BOTTOM, WOOD_FLOOR, COBBLESTONE,
						BLUE_RUG_TL, BLUE_RUG_TR, BLUE_RUG_BL, BLUE_RUG_BR,
						RED_RUG_TL, RED_RUG_TR, RED_RUG_BL, RED_RUG_BR;
	
	public static Image HOUSE_1_TL, HOUSE_1_L, HOUSE_1_BL, HOUSE_1_BASE_L,
						HOUSE_1_TR, HOUSE_1_R, HOUSE_1_BR, HOUSE_1_BASE_R,
						HOUSE_1_T, HOUSE_1_M, HOUSE_1_INNER, HOUSE_1_WINDOW, HOUSE_1_WALL,
						HOUSE_1_DOOR_L, HOUSE_1_DOOR_R, HOUSE_1_DOOR_TL, HOUSE_1_DOOR_TR;
	
	public static Image HOUSE_1_INNER_SL, HOUSE_1_INNER_SR, HOUSE_1_INNER_TOP, HOUSE_1_INNER_CR,
						HOUSE_1_INNER_CL, HOUSE_1_INNER_VERTICAL_L, HOUSE_1_INNER_VERTICAL_R, 
						HOUSE_1_INNER_STL, HOUSE_1_INNER_STR, HOUSE_1_CONCAVE_CBL, HOUSE_1_CONCAVE_CBR,
						HOUSE_1_CONCAVE_SL, HOUSE_1_CONCAVE_SR;
	
	public static Image WELL;
	
	
	
	
	
	public Assets() {
		try {
			// Load the sprite sheets.
			URL res1 = getClass().getResource("/resources/spritesheet.png");
			URL res2 = getClass().getResource("/resources/people.png");
			
			spritesheet = ImageIO.read( res1 );
			playerSpritesheet = ImageIO.read( res2 );			
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	
	public void initialize() {
		InputStream tileLocs = getClass().getResourceAsStream("/resources/maps/TileLocations.txt");
		BufferedReader reader = null;

		// Load the text file.
		try {
			reader = new BufferedReader(new InputStreamReader(tileLocs));

			String str = reader.readLine();
			while(str != null) {
				String[] parts = str.split(" ");
        	
	        	String id = parts[0];
	        	int x = Integer.parseInt(parts[1]);
	        	int y = Integer.parseInt(parts[2]);
	        	int w = Integer.parseInt(parts[3]);
	        	int h = Integer.parseInt(parts[4]);
	        	String ssType = ((String)parts[5]).replace("\n","");
	        	
	        	try {
	        		Field e = Assets.class.getDeclaredField(id);
	        		e.setAccessible(true);

					if(ssType.startsWith("PEOPLE")) {
        				Image val = getSprite(playerSpritesheet, x, y, w, h);
        				e.set(this, val);
        			}
        			else if(ssType.startsWith("SPRITESHEET")) {
        				Image val = getSprite(spritesheet, x, y, w, h);
        				e.set(this, val);
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



		/* People */
		// PLAYER_UP = getSprite(playerSpritesheet, 32, 0, 32, 32);
		// PLAYER_DOWN = getSprite(playerSpritesheet, 0, 0, 32, 32);
		// PLAYER_LEFT = getSprite(playerSpritesheet, 96, 0, 32, 32);
		// PLAYER_RIGHT = getSprite(playerSpritesheet, 64, 0, 32, 32);
		// PLAYER_WALK_UP_1 = getSprite(playerSpritesheet, 32, 32, 32, 32);
		// PLAYER_WALK_UP_2 = getSprite(playerSpritesheet, 32, 64, 32, 32);
		// PLAYER_WALK_DOWN_1 = getSprite(playerSpritesheet, 0, 32, 32, 32);
		// PLAYER_WALK_DOWN_2 = getSprite(playerSpritesheet, 0, 64, 32, 32);
		// PLAYER_WALK_LEFT_1 = getSprite(playerSpritesheet, 96, 32, 32, 32);
		// PLAYER_WALK_LEFT_2 = getSprite(playerSpritesheet, 96, 64, 32, 32);
		// PLAYER_WALK_RIGHT_1 = getSprite(playerSpritesheet, 64, 32, 32, 32);
		// PLAYER_WALK_RIGHT_2 = getSprite(playerSpritesheet, 64, 64, 32, 32);

		
		// /* INTERACTABLE OBJECTS */
		// WELL = getSprite(spritesheet, 64, 0, 32, 32);
		
		

		// /* Environment */
		// EMPTY = getSprite(spritesheet, 608, 368, 32, 32);
		// GRASS_1 = getSprite(spritesheet, 0, 0, 32, 32);
		// TREE_TOP = getSprite(spritesheet, 0, 32, 32, 32);
		// TREE_BOTTOM = getSprite(spritesheet, 0, 64, 32, 32);
		// WOOD_FLOOR = getSprite(spritesheet, 32, 32, 32, 32);
		// COBBLESTONE = getSprite(spritesheet, 32, 0, 32, 32);
		// BLUE_RUG_TL = getSprite(spritesheet, 64, 32, 32, 32);
		// BLUE_RUG_TR = getSprite(spritesheet, 96, 32, 32, 32);
		// BLUE_RUG_BL = getSprite(spritesheet, 64, 64, 32, 32);
		// BLUE_RUG_BR = getSprite(spritesheet, 96, 64, 32, 32);
		// RED_RUG_TL = getSprite(spritesheet, 128, 32, 32, 32);
		// RED_RUG_TR = getSprite(spritesheet, 160, 32, 32, 32);
		// RED_RUG_BL = getSprite(spritesheet, 128, 64, 32, 32);
		// RED_RUG_BR = getSprite(spritesheet, 160, 64, 32, 32);
		
		// HOUSE_1_TL = getSprite(spritesheet, 192, 64, 32, 32);
		// HOUSE_1_L = getSprite(spritesheet, 192, 96, 32, 32); 
		// HOUSE_1_BL = getSprite(spritesheet, 192, 128, 32, 32); 
		// HOUSE_1_BASE_L = getSprite(spritesheet, 192, 160, 32, 32);
		// HOUSE_1_TR = getSprite(spritesheet, 256, 64, 32, 32); 
		// HOUSE_1_R = getSprite(spritesheet, 256, 96, 32, 32); 
		// HOUSE_1_BR = getSprite(spritesheet, 256, 128, 32, 32);
		// HOUSE_1_BASE_R = getSprite(spritesheet, 256, 160, 32, 32);
		// HOUSE_1_T = getSprite(spritesheet, 224, 64, 32, 32);
		// HOUSE_1_M = getSprite(spritesheet, 224, 96, 32, 32);
		// HOUSE_1_INNER = getSprite(spritesheet, 288, 128, 32, 32);
		// HOUSE_1_WINDOW = getSprite(spritesheet, 320, 160, 32, 32);
		// HOUSE_1_WALL = getSprite(spritesheet, 288, 160, 32, 32);
		// HOUSE_1_DOOR_L = getSprite(spritesheet, 352, 160, 32, 32);
		// HOUSE_1_DOOR_R = getSprite(spritesheet, 384, 160, 32, 32);
		// HOUSE_1_DOOR_TL = getSprite(spritesheet, 352, 128, 32, 32);
		// HOUSE_1_DOOR_TR = getSprite(spritesheet, 384, 128, 32, 32);
		
		// HOUSE_1_INNER_SL = getSprite(spritesheet, 352, 64, 32, 32);
		// HOUSE_1_INNER_SR = getSprite(spritesheet, 384, 32, 32, 32);
		// HOUSE_1_INNER_TOP = getSprite(spritesheet, 352, 96, 32, 32);
		// HOUSE_1_INNER_CR = getSprite(spritesheet, 384, 96, 32, 32);
		// HOUSE_1_INNER_CL = getSprite(spritesheet, 416, 96, 32, 32);
		// HOUSE_1_INNER_VERTICAL_L = getSprite(spritesheet, 384, 64, 32, 32);
		// HOUSE_1_INNER_VERTICAL_R = getSprite(spritesheet, 416, 64, 32, 32);
		// HOUSE_1_INNER_STL = getSprite(spritesheet, 384, 32, 32, 32);
		// HOUSE_1_INNER_STR = getSprite(spritesheet, 416, 32, 32, 32);
		// HOUSE_1_CONCAVE_CBL = getSprite(spritesheet, 320, 64, 32, 32);
		// HOUSE_1_CONCAVE_CBR = getSprite(spritesheet, 288, 64, 32, 32);
		// HOUSE_1_CONCAVE_SL = getSprite(spritesheet, 320, 96, 32, 32);
		// HOUSE_1_CONCAVE_SR = getSprite(spritesheet, 288, 96, 32, 32);
	}
	
	

	public Image getSprite(BufferedImage ss, int x, int y, int width, int height) {
		return SwingFXUtils.toFXImage(ss.getSubimage(x, y, width, height), null);
	}


	public Image fromString(String name) {
		try {
			Field e = Assets.class.getDeclaredField(name);
		    e.setAccessible(true);
		    Image img = (Image)e.get(this);
		    return img;
		} catch(Exception err) {
			return new Image("");
		}
	}
	
}