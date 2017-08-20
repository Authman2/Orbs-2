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
import controllers.*;

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
	public static Image ELDERY_WOMAN_UP, ELDERY_WOMAN_DOWN, ELDERY_WOMAN_LEFT, ELDERY_WOMAN_RIGHT;
	public static Image BOY_1_UP, BOY_1_DOWN, BOY_1_LEFT, BOY_1_RIGHT,
						BOY_2_UP, BOY_2_DOWN, BOY_2_LEFT, BOY_2_RIGHT,
						BOY_3_UP, BOY_3_DOWN, BOY_3_LEFT, BOY_3_RIGHT,
						GIRL_1_UP, GIRL_1_DOWN, GIRL_1_LEFT, GIRL_1_RIGHT,
						GIRL_2_UP, GIRL_2_DOWN, GIRL_2_LEFT, GIRL_2_RIGHT,
						GIRL_3_UP, GIRL_3_DOWN, GIRL_3_LEFT, GIRL_3_RIGHT;
	
	public static Image MAN_1_UP, MAN_1_DOWN, MAN_1_LEFT, MAN_1_RIGHT,
						MAN_2_UP, MAN_2_DOWN, MAN_2_LEFT, MAN_2_RIGHT,
						WOMEN_1_UP, WOMEN_1_DOWN, WOMEN_1_LEFT, WOMEN_1_RIGHT,
						WOMEN_2_UP, WOMEN_2_DOWN, WOMEN_2_LEFT, WOMEN_2_RIGHT;
	
	public static Image EMPTY, GRASS_1, TREE_TOP, TREE_BOTTOM, WOOD_FLOOR, COBBLESTONE,
						BLUE_RUG_TL, BLUE_RUG_TR, BLUE_RUG_BL, BLUE_RUG_BR,
						RED_RUG_TL, RED_RUG_TR, RED_RUG_BL, RED_RUG_BR;
	
	public static Image HOUSE_1_TL, HOUSE_1_L, HOUSE_1_BL, HOUSE_1_BASE_L,
						HOUSE_1_TR, HOUSE_1_R, HOUSE_1_BR, HOUSE_1_BASE_R,
						HOUSE_1_T, HOUSE_1_M, HOUSE_1_INNER, HOUSE_1_WINDOW, HOUSE_1_WALL,
						HOUSE_1_DOOR_L, HOUSE_1_DOOR_R, HOUSE_1_DOOR_TL, HOUSE_1_DOOR_TR,
                        HOUSE_1_VERTICAL_WALL_L, HOUSE_1_VERTICAL_WALL_R,
                        HOUSE_INNERWALL_FACING_LEFT, HOUSE_INNERWALL_FACING_RIGHT;
	
	public static Image HOUSE_1_INNER_SL, HOUSE_1_INNER_SR, HOUSE_1_INNER_TOP, HOUSE_1_INNER_CR,
						HOUSE_1_INNER_CL, HOUSE_1_INNER_VERTICAL_L, HOUSE_1_INNER_VERTICAL_R, 
						HOUSE_1_INNER_STL, HOUSE_1_INNER_STR, HOUSE_1_CONCAVE_CBL, HOUSE_1_CONCAVE_CBR,
						HOUSE_1_CONCAVE_SL, HOUSE_1_CONCAVE_SR, HOUSE_INNER_CONVEX_CBL, HOUSE_INNER_CONVEX_CBR;
    
    public static Image SAND_TOP_LEFT, SAND_TOP, SAND_TOP_RIGHT, SAND_RIGHT, SAND_BOTTOM_RIGHT, SAND_BOTTOM,
                        SAND_BOTTOM_LEFT, SAND_LEFT, SAND;
    public static Image WATER_TOP_LEFT, WATER_TOP, WATER_TOP_RIGHT, WATER_RIGHT, WATER_BOTTOM_RIGHT, WATER_BOTTOM,
                        WATER_BOTTOM_LEFT, WATER_LEFT, WATER;
    public static Image TV_LEFT, TV_RIGHT, TV, LAMP_TOP, LAMP_BOTTOM, TABLE_TOP_LEFT, TABLE_TOP_RIGHT,
                        TABLE_BOTTOM_LEFT, TABLE_BOTTOM_RIGHT;
    public static Image ROAD_MIDDLE, ROAD_SIDEWAYS, ROAD;
	
	public static Image WELL, DOOR, ROCK, DOOR_BLOCK, GENERATOR;
	
	public static Image GRASS_2, FLOWER_1, FLOWER_2, BREAKABLE_TREE, WHIRLPOOL, TOWEL_1_TOP, TOWEL_1_BOTTOM,
                        TOWEL_2_TOP, TOWEL_2_BOTTOM;
    
    public static Image SWAMP_TOP_LEFT, SWAMP_TOP, SWAMP_TOP_RIGHT, SWAMP_RIGHT, SWAMP_BOTTOM_RIGHT, SWAMP_BOTTOM,
                        SWAMP_BOTTOM_LEFT, SWAMP_LEFT, SWAMP;
    
    public static Image DOG_1_UP, DOG_1_DOWN, DOG_1_LEFT, DOG_1_RIGHT,
                        DOG_2_UP, DOG_2_DOWN, DOG_2_LEFT, DOG_2_RIGHT,
                        HORSE_UP, HORSE_DOWN, HORSE_LEFT, HORSE_RIGHT;
    
    
	
	
	
	public Assets() {
		spritesheet = Networking.loadBufferedImage("spritesheet.png");
		playerSpritesheet = Networking.loadBufferedImage("people.png");
	}
	
	
	
	public void initialize() {
		// Loads and sets up all the tile locations from the file.
		ArrayList<String> lines = Networking.read("maps/TileLocations.txt");
		for(String line : lines) {
			String[] parts = line.split(" ");
        	
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
		}
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