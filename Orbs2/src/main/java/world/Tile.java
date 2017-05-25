package world;

import javafx.scene.image.Image;

import je.visual.Vector2D;

import states.WorldState;
import entities.*;

public class Tile extends Entity {


	WorldState worldState;


	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public Tile(Vector2D pos, Image img, WorldState ws) {
		super(pos, ws);
		this.setSprites(new Image[] {img, img, img, img} );
		this.worldState = ws;
	}

} // End of class.