package states;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	Stage stage;
	StackPane root;
	Scene scene;
	public GameController gc;


	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public GameState(GameController gc, Stage stage) {
		this.gc = gc;
		this.stage = stage;
		this.root = new StackPane();
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

	public Scene getScene() {
		return scene;
	}



	/********************
	*					*
	*	   ABSTRACT		*
	*					*
	*********************/

	public abstract void initialize();

	public abstract void update();

	public abstract void draw();



} // End of class.