package com.example.project.visualisationgeneratorwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VisualisationGeneratorWindow {

    private Stage primaryStage;
    private Parent root;
    private Scene scene;

    public VisualisationGeneratorWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initializeStage() {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(VisualisationGeneratorWindow.class.getResource("VisualisationGeneratorScene.fxml")));
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
