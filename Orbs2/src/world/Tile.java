package world;

import javafx.scene.image.Image;

import je.visual.Vector2D;

import states.WorldState;
import entities.*;

public class Tile extends Entity {

	/********************
	 *					*
	 *     VARIABLES	*
	 *					*
	 *********************/

	WorldState worldState;

	boolean solid;

	int type;




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


	/** Determines whether or not this tile should be solid based on its type. */
	private void determineSolidity() {
        switch (type) {
            case 0: solid = false; break;
            case 20: solid = true; break;
            case 40: solid = true; break;

            default: solid = false; break;
        }
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


    /** Sets the type of this tile. */
    public void setType(int t) {
	    this.type = t;
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

        this.determineSolidity();
    }

    public void update() {
        super.update();
    }

    public void draw() {
        super.draw();
    }

} // End of class.