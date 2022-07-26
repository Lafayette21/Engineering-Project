package com.example.project.startwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartWindow {
    private StartWindow() {
        throw new RuntimeException("Class StartWindow cannot be instantiated");
    }

    public static void initialize(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(StartWindow.class.getResource("StartScene.fxml")));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
