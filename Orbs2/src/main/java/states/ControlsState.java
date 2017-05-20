package states;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import main_package.Orbs2;
import controllers.GameController;

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