package main_package;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import je.visual.JEImage;

public class Assets {

	// Spritesheets
	BufferedImage spritesheet;
	
	
	// Sprites
	public static Image GRASS_1, TREE_TOP, TREE_BOTTOM;
	
	
	
	
	
	public Assets() {
		try {
			spritesheet = ImageIO.read(new File("/Users/adeolauthman/Documents/AdeolasCodingStuff/JavaPrograms/Orbs2/resources/spritesheet.png")); // eventually C:\\ImageTest\\pic2.jpg
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	
	public void initialize() {
		GRASS_1 = SwingFXUtils.toFXImage(spritesheet.getSubimage(0, 0, 32, 32), null);
		TREE_TOP = SwingFXUtils.toFXImage(spritesheet.getSubimage(0, 32, 32, 32), null);
		TREE_BOTTOM = SwingFXUtils.toFXImage(spritesheet.getSubimage(0, 64, 32, 32), null);
	}
	
	
	
}