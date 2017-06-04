package main_package;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Assets {

	// Spritesheets
	BufferedImage spritesheet, playerSpritesheet;
	
	
	// Sprites
	public static Image PLAYER_UP, PLAYER_DOWN, PLAYER_LEFT, PLAYER_RIGHT;
	public static Image PLAYER_WALK_UP_1, PLAYER_WALK_UP_2,
						PLAYER_WALK_DOWN_1, PLAYER_WALK_DOWN_2,
						PLAYER_WALK_LEFT_1, PLAYER_WALK_LEFT_2,
						PLAYER_WALK_RIGHT_1, PLAYER_WALK_RIGHT_2;
	
	public static Image EMPTY, GRASS_1, TREE_TOP, TREE_BOTTOM, WOOD_FLOOR, COBBLESTONE,
						BLUE_RUG_TL, BLUE_RUG_TR, BLUE_RUG_BL, BLUE_RUG_BR,
						RED_RUG_TL, RED_RUG_TR, RED_RUG_BL, RED_RUG_BR;
	
	public static Image HOUSE_1_TL, HOUSE_1_L, HOUSE_1_BL, HOUSE_1_BASE_L,
						HOUSE_1_TR, HOUSE_1_R, HOUSE_1_BR, HOUSE_1_BASE_R,
						HOUSE_1_T, HOUSE_1_M, HOUSE_1_INNER, HOUSE_1_WINDOW, HOUSE_1_WALL,
						HOUSE_1_DOOR_L, HOUSE_1_DOOR_R, HOUSE_1_DOOR_TL, HOUSE_1_DOOR_TR;
	
	public static Image HOUSE_1_INNER_SL, HOUSE_1_INNER_SR, HOUSE_1_INNER_TOP, HOUSE_1_INNER_CR,
						HOUSE_1_INNER_CL, HOUSE_1_INNER_VERTICAL_L,
						HOUSE_1_INNER_VERTICAL_R, HOUSE_1_INNER_ST;
	
	public static Image WELL;
	
	
	
	
	
	public Assets() {
		try {
			spritesheet = ImageIO.read( getClass().getResource("../resources/spritesheet.png" ) );
			playerSpritesheet = ImageIO.read( getClass().getResource("../resources/people.png" ) );
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	
	public void initialize() {
		/* People */
		PLAYER_UP = getSprite(playerSpritesheet, 32, 0, 32, 32);
		PLAYER_DOWN = getSprite(playerSpritesheet, 0, 0, 32, 32);
		PLAYER_LEFT = getSprite(playerSpritesheet, 96, 0, 32, 32);
		PLAYER_RIGHT = getSprite(playerSpritesheet, 64, 0, 32, 32);
		PLAYER_WALK_UP_1 = getSprite(playerSpritesheet, 32, 32, 32, 32);
		PLAYER_WALK_UP_2 = getSprite(playerSpritesheet, 32, 64, 32, 32);
		PLAYER_WALK_DOWN_1 = getSprite(playerSpritesheet, 0, 32, 32, 32);
		PLAYER_WALK_DOWN_2 = getSprite(playerSpritesheet, 0, 64, 32, 32);
		PLAYER_WALK_LEFT_1 = getSprite(playerSpritesheet, 96, 32, 32, 32);
		PLAYER_WALK_LEFT_2 = getSprite(playerSpritesheet, 96, 64, 32, 32);
		PLAYER_WALK_RIGHT_1 = getSprite(playerSpritesheet, 64, 32, 32, 32);
		PLAYER_WALK_RIGHT_2 = getSprite(playerSpritesheet, 64, 64, 32, 32);

		
		/* INTERACTABLE OBJECTS */
		WELL = getSprite(spritesheet, 64, 0, 32, 32);
		
		

		/* Environment */
		EMPTY = getSprite(spritesheet, 608, 368, 32, 32);
		GRASS_1 = getSprite(spritesheet, 0, 0, 32, 32);
		TREE_TOP = getSprite(spritesheet, 0, 32, 32, 32);
		TREE_BOTTOM = getSprite(spritesheet, 0, 64, 32, 32);
		WOOD_FLOOR = getSprite(spritesheet, 32, 32, 32, 32);
		COBBLESTONE = getSprite(spritesheet, 32, 0, 32, 32);
		BLUE_RUG_TL = getSprite(spritesheet, 64, 32, 32, 32);
		BLUE_RUG_TR = getSprite(spritesheet, 96, 32, 32, 32);
		BLUE_RUG_BL = getSprite(spritesheet, 64, 64, 32, 32);
		BLUE_RUG_BR = getSprite(spritesheet, 96, 64, 32, 32);
		RED_RUG_TL = getSprite(spritesheet, 128, 32, 32, 32);
		RED_RUG_TR = getSprite(spritesheet, 160, 32, 32, 32);
		RED_RUG_BL = getSprite(spritesheet, 128, 64, 32, 32);
		RED_RUG_BR = getSprite(spritesheet, 160, 64, 32, 32);
		
		HOUSE_1_TL = getSprite(spritesheet, 192, 64, 32, 32);
		HOUSE_1_L = getSprite(spritesheet, 192, 96, 32, 32); 
		HOUSE_1_BL = getSprite(spritesheet, 192, 128, 32, 32); 
		HOUSE_1_BASE_L = getSprite(spritesheet, 192, 160, 32, 32);
		HOUSE_1_TR = getSprite(spritesheet, 256, 64, 32, 32); 
		HOUSE_1_R = getSprite(spritesheet, 256, 96, 32, 32); 
		HOUSE_1_BR = getSprite(spritesheet, 256, 128, 32, 32);
		HOUSE_1_BASE_R = getSprite(spritesheet, 256, 160, 32, 32);
		HOUSE_1_T = getSprite(spritesheet, 224, 64, 32, 32);
		HOUSE_1_M = getSprite(spritesheet, 224, 96, 32, 32);
		HOUSE_1_INNER = getSprite(spritesheet, 288, 128, 32, 32);
		HOUSE_1_WINDOW = getSprite(spritesheet, 320, 160, 32, 32);
		HOUSE_1_WALL = getSprite(spritesheet, 288, 160, 32, 32);
		HOUSE_1_DOOR_L = getSprite(spritesheet, 352, 160, 32, 32);
		HOUSE_1_DOOR_R = getSprite(spritesheet, 384, 160, 32, 32);
		HOUSE_1_DOOR_TL = getSprite(spritesheet, 352, 128, 32, 32);
		HOUSE_1_DOOR_TR = getSprite(spritesheet, 384, 128, 32, 32);
		
		HOUSE_1_INNER_SL = getSprite(spritesheet, 352, 64, 32, 32);
		HOUSE_1_INNER_SR = getSprite(spritesheet, 384, 32, 32, 32);
		HOUSE_1_INNER_TOP = getSprite(spritesheet, 384, 128, 32, 32);
		HOUSE_1_INNER_CR = getSprite(spritesheet, 384, 96, 32, 32);
		HOUSE_1_INNER_CL = getSprite(spritesheet, 416, 96, 32, 32);
		HOUSE_1_INNER_VERTICAL_L = getSprite(spritesheet, 416, 64, 32, 32);
		HOUSE_1_INNER_VERTICAL_R = getSprite(spritesheet, 448, 64, 32, 32);
		HOUSE_1_INNER_ST = getSprite(spritesheet, 416, 32, 32, 32);
	}
	
	

	public Image getSprite(BufferedImage ss, int x, int y, int width, int height) {
		return SwingFXUtils.toFXImage(ss.getSubimage(x, y, width, height), null);
	}
	
}