package states;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main_package.*;

public class ViewTasksState extends GameState {

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

	public ViewTasksState(GameController gc, Stage stage) {
		super(gc, stage);



		// IMPORTANT: Scene Setup
		scene = new Scene(root, Orbs2.WIDTH, Orbs2.HEIGHT);
		setupKeyActions();
	}


	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	private void setupKeyActions() {
		scene.setOnKeyPressed(e -> {
			KeyCode w = e.getCode();
			
			switch(w) {
				case ESCAPE:
					gc.goTo(1);
					break;
			}
		});
		scene.setOnKeyReleased(e -> {
			KeyCode w = e.getCode();
		});
	}
	


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
		




		
	}

	public void update() {

	}

	public void draw() {

	}


}