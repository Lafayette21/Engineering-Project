package com.example.project;

import com.example.project.controller.MainApplicationScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApplicationScreenController mainController = createAndSetMainApplicationController();

        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MainApplicationScreenController createAndSetMainApplicationController() {
        MainApplicationScreenController mainController = new MainApplicationScreenController();
        mainController.loadScreen(Resource.StartWindow);
        mainController.loadScreen(Resource.VisualisationGenerator);

        mainController.setScreen(Resource.StartWindow);
        return mainController;
    }

    public static void main(String[] args) {
        launch();
    }
}