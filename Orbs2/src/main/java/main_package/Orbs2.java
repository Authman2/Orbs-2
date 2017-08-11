package main_package;

import controllers.GameController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Orbs2 extends Application {
	public static final int WIDTH = 640, HEIGHT = 480;



    public static void main(String[] args) {
        // Launch app.
    	Application.launch(args);
    }


    @Override public void start(Stage stage) {
        // The game controller.
        GameController gc = new GameController(stage);
        gc.initialize();


        // Setup the scene.
        stage.setOnCloseRequest(e-> {
            
        });
        stage.setScene(gc.getCurrentState().getScene());
		stage.setTitle("Orbs");
        stage.show(); 
    }

}