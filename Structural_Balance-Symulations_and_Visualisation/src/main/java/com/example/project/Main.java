package com.example.project;

import com.example.project.controller.MainApplicationScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainApplicationScreenController mainController = createAndSetMainApplicationController();

        prepareStartScreen(primaryStage, mainController);
    }

    private MainApplicationScreenController createAndSetMainApplicationController() {
        MainApplicationScreenController mainController = new MainApplicationScreenController();
        mainController.loadScreen(Resource.StartWindow);
        mainController.loadScreen(Resource.VisualisationGenerator);
        mainController.loadScreen(Resource.Visualisation);

        mainController.setScreen(Resource.StartWindow);
        return mainController;
    }

    private void prepareStartScreen(Stage primaryStage, MainApplicationScreenController mainController) {
        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}