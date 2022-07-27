package com.example.project.startwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartWindow {

    private Stage primaryStage;
    private Parent root;
    private Scene scene;

    public StartWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initializeStage() {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(StartWindow.class.getResource("StartScene.fxml")));
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Cannot Initialize stage");
        }
    }

}
