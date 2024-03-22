package com.finalproj;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private GameState gameState;

    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDirectory);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        gameState = GameState.WELCOME;
        new WelcomeScreen(stage, gameState).show();
    }
}
