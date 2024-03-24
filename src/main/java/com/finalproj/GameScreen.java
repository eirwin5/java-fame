package com.finalproj;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameScreen implements Screen {
    private Stage stage;
    private GameState gameState;
    private List<Level> levels;
    private int currentLevelIndex = 0;
    private MediaPlayer mediaPlayer;

    public GameScreen(Stage stage, GameState gameState, List<Level> levels) {
        this.stage = stage;
        this.gameState = gameState;
        this.levels = levels;
    }

    @Override
    public void show() {
        switch (gameState) {
            case GAME:
                loadLevelContent();
                break;
            case GAME_OVER:
                showGameOver();
                break;
            case GAME_WIN:
                showGameWin();
                break;
            default:
                break;
        }
    }

    private void loadLevelContent() {
        if (currentLevelIndex >= levels.size()) {
            gameState = GameState.GAME_WIN;
            showGameWin();
            return;
        }

        Level currentLevel = levels.get(currentLevelIndex);
        Song song = currentLevel.getSong();
        playMusic(song);

        Button nextLevelButton = new Button("Next Level");
        nextLevelButton.setOnAction(e -> nextLevel());

        Label levelLabel = new Label("Current level: " + (currentLevelIndex + 1));

        VBox gameLayout = new VBox(20);
        gameLayout.setAlignment(Pos.CENTER);
        gameLayout.getChildren().addAll(levelLabel, nextLevelButton);

        Scene gameScene = new Scene(gameLayout, 400, 300);
        stage.setScene(gameScene);
        stage.show();
    }

    private void playMusic(Song song) {
        Media media = new Media(new File(song.getLink()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void showGameOver() {
        new GameOverScreen(stage, gameState).show();
    }

    private void showGameWin() {
        new GameWinScreen(stage, gameState).show();
    }

    public void nextLevel() {
        stopMusic();
        currentLevelIndex++;

        loadLevelContent();
    }
}
