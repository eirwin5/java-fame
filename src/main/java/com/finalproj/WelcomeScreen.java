package com.finalproj;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeScreen implements Screen {
    private Stage stage;
    private WelcomeScreenListener listener;

    public WelcomeScreen(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            if (listener != null) {
                listener.onStartGameClicked();
            }
        });

        StackPane welcomeLayout = new StackPane();
        welcomeLayout.getChildren().add(startButton);

        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);
        stage.setScene(welcomeScene);
        stage.show();
    }

    public void setWelcomeScreenListener(WelcomeScreenListener listener) {
        this.listener = listener;
    }

    public interface WelcomeScreenListener {
        void onStartGameClicked();
    }
}