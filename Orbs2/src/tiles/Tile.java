package tiles;

import entities.Entity;
import javafx.scene.image.Image;
import je.visual.Vector2D;
import states.WorldState;

public class Tile extends Entity {

	/********************
	 *					*
	 *     VARIABLES	*
	 *					*
	 *********************/

	WorldState worldState;

	boolean solid;




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


	/********************
	 *					*
	 *     SETTERS		*
	 *					*
	 *********************/

	/** Sets whether or not this tile is solid. */
	public void setSolid(boolean b) {
	    this.solid = b;
    }

    
    
    /********************
	 *					*
	 *     GETTERS		*
	 *					*
	 *********************/

    public boolean isSolid() {
    	return solid;
    }



    /********************
     *					*
     *     ABSTRACT 	*
     *					*
     *********************/

    public void initialize() {
        super.initialize();
    }

    public void update() {
        super.update();
    }

    public void draw() {
        super.draw();
    }

} // End of class.