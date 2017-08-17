package states;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.stream.Collectors;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main_package.*;
import tasks.*;

import je.visual.Vector2D;

public class ViewTasksState extends GameState {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	TextArea textView = new TextArea();



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	public ViewTasksState(GameController gc, Stage stage) {
		super(gc, stage);
		
		this.handleLayout();
	}


	private void handleLayout() {
		textView.setEditable(false);
		textView.setMaxWidth(Orbs2.WIDTH);
		textView.setMaxHeight(Orbs2.HEIGHT);

		// IMPORTANT: Scene Setup
		root.getChildren().add(textView);
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
		// Set the text of the tasks
		StringBuilder builder = new StringBuilder();
		builder.append("Click the 'Escape' key to go back to the game.\n"
						+ "Tasks with \"\u2715\" next to them have not been completed, those with \"\u2713\" have been completed. \n\n");

		List<Task> tasks = TaskSystem.getAllTasks().stream().filter( e -> e.isStarted() ).collect(Collectors.toList());
		for(Task t : tasks) {
			//System.out.println(t);
			if(t.isCompleted()) {
				builder.append( "(\u2713)    " + t.getTaskDescription() + "\n");
			} else {
				builder.append( "(\u2715)    " + t.getTaskDescription() + "\n");
			}
			//3642550
		}
		textView.setText(builder.toString());
	}

	public void update() {

	}

	public void draw() {
		
	}


}