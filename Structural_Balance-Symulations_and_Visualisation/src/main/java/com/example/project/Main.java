package com.example.project;

import com.example.project.controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String startScreen = "StartScreen";
    public static String startScreenResourceName = "StartScreen.fxml";

    public static String visualisationGenerator = "VisualisationGenerator";
    public static String visualisationGeneratorResourceName = "VisualisationGeneratorScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenController mainController = new ScreenController();
        mainController.loadScreen(startScreen,startScreenResourceName);
        mainController.loadScreen(visualisationGenerator,visualisationGeneratorResourceName);

        mainController.setScreen(startScreen);

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