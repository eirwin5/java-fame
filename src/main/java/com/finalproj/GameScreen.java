package com.finalproj;

import java.io.File;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

class GameScreen implements Screen {
    private Stage stage;
    private GameState gameState;
    private List<Song> songs;

    GameScreen(Stage stage, GameState gameState) {
        this.stage = stage;
        this.gameState = gameState;
        this.songs = Song.readSongsFromJson();
    }

    @Override
    public void show() {
        Song backgroundMusic = songs.get(0);
        Media media = new Media(new File(backgroundMusic.getLink()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        backgroundMusic.playMusic(mediaPlayer);

        Button gameOverButton = new Button("Game Over");
        gameOverButton.setOnAction(e -> {
            backgroundMusic.stopMusic(mediaPlayer);
            gameState = GameState.GAME_OVER;
            new GameOverScreen(stage, gameState).show();
        });

        StackPane gameLayout = new StackPane();
        gameLayout.getChildren().add(gameOverButton);

        Scene gameScene = new Scene(gameLayout, 400, 300);
        stage.setScene(gameScene);
        stage.show();
    }
}
