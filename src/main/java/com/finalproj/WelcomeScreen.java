package com.finalproj;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

class WelcomeScreen implements Screen {
    private Stage stage;
    private GameState gameState;

    WelcomeScreen(Stage stage, GameState gameState) {
        this.stage = stage;
        this.gameState = gameState;
    }

    @Override
    public void show() {
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            gameState = GameState.GAME;
            new GameScreen(stage, gameState).show();
        });

        StackPane welcomeLayout = new StackPane();
        welcomeLayout.getChildren().add(startButton);

        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);
        stage.setScene(welcomeScene);
        stage.show();
    }
}