package main_package;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import controllers.GameController;

import java.io.*;
import java.util.*;

public class Orbs2 extends Application {
	public static final int WIDTH = 640, HEIGHT = 480;



    public static void main(String[] args) {
    	Application.launch(args);
    }


    @Override public void start(Stage stage) {
        // The game controller.
        GameController gc = new GameController(stage);
        gc.initialize();


        // Setup the scene.
        stage.setScene(gc.getCurrentState().getScene());
        stage.setResizable(false);
		stage.setTitle("Orbs");
        stage.show(); 
    }
}