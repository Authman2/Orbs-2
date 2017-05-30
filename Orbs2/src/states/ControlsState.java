package states;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main_package.Orbs2;

public class ControlsState extends GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public ControlsState(GameController gc, Stage stage) {
		super(gc, stage);

	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	


	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/




	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public void initialize() {
		




		// IMPORTANT: Scene Setup
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
	}

	public void update() {

	}

	public void draw() {

	}



} // End of class.