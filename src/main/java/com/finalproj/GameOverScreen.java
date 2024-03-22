package com.finalproj;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOverScreen implements Screen {
    private Stage stage;
    private GameState gameState;

    GameOverScreen(Stage stage, GameState gameState) {
        this.stage = stage;
        this.gameState = gameState;
    }

    @Override
    public void show() {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("Game Over!");
        gameOverText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> {
            gameState = GameState.WELCOME;
            new WelcomeScreen(stage, gameState).show();
        });

        gameOverLayout.getChildren().addAll(gameOverText, restartButton);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gameOverLayout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        Scene gameOverScene = new Scene(gameOverLayout, 400, 300);
        stage.setScene(gameOverScene);
        stage.show();
    }
}
