package com.adeolauthman.Orbs2.main;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import je.visual.JEImage;

public class Assets {

	// Spritesheets
	JEImage spritesheet;
	
	
	// Sprites
	public static Image grass_1, tree_1_top, tree_2_top;
	
	
	
	
	
	public Assets() {
		spritesheet = new JEImage(Orbs2.class, "../resources/spritesheet.png");
	}
	
	
	
	public void initialize() {
		grass_1 = SwingFXUtils.toFXImage(spritesheet.getPart(0, 0, 32, 32), null);
		tree_1_top = SwingFXUtils.toFXImage(spritesheet.getPart(0, 32, 32, 32), null);
		tree_2_top = SwingFXUtils.toFXImage(spritesheet.getPart(0, 64, 32, 32), null);
	}
	
	
	
}
