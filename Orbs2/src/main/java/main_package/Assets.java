package main_package;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import je.visual.JEImage;

public class Assets {

	// Spritesheets
	BufferedImage spritesheet, playerSpritesheet;
	
	
	// Sprites
	public static Image PLAYER_UP, PLAYER_DOWN, PLAYER_LEFT, PLAYER_RIGHT;
	public static Image PLAYER_WALK_UP_1, PLAYER_WALK_UP_2,
						PLAYER_WALK_DOWN_1, PLAYER_WALK_DOWN_2,
						PLAYER_WALK_LEFT_1, PLAYER_WALK_LEFT_2,
						PLAYER_WALK_RIGHT_1, PLAYER_WALK_RIGHT_2;
	public static Image GRASS_1, TREE_TOP, TREE_BOTTOM;
	
	
	
	
	
	public Assets() {
		try {
			spritesheet = ImageIO.read(new File("/Users/adeolauthman/Documents/AdeolasCodingStuff/JavaPrograms/Orbs2/resources/spritesheet.png")); 
			playerSpritesheet = ImageIO.read(new File("/Users/adeolauthman/Documents/AdeolasCodingStuff/JavaPrograms/Orbs2/resources/people.png"));
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


		/* Environment */
		GRASS_1 = getSprite(spritesheet, 0, 0, 32, 32);
		TREE_TOP = getSprite(spritesheet, 0, 32, 32, 32);
		TREE_BOTTOM = getSprite(spritesheet, 0, 64, 32, 32);
	}
	
	

	public Image getSprite(BufferedImage ss, int x, int y, int width, int height) {
		return SwingFXUtils.toFXImage(ss.getSubimage(x, y, width, height), null);
	}
	
}