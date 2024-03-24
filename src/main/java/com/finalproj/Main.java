package com.finalproj;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements WelcomeScreen.WelcomeScreenListener {
    private Stage stage;
    private GameState gameState;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        gameState = GameState.WELCOME;

        WelcomeScreen welcomeScreen = new WelcomeScreen(stage);
        welcomeScreen.setWelcomeScreenListener(this);
        welcomeScreen.show();
    }

    @Override
    public void onStartGameClicked() {
        List<Song> songs = Song.readSongsFromJson();
        Level level1 = new Level(0, songs);
        Level level2 = new Level(1, songs);
        Level level3 = new Level(2, songs);
        List<Level> levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        // Initialize GameScreen
        new GameScreen(stage, GameState.GAME, levels).show();
    }
}
