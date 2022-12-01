package com.example.project;

import com.example.project.controller.MainApplicationScreenController;
import com.example.project.database.repository.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainApplicationScreenController mainController = MainApplicationScreenController.getInstance();
        prepareRepositoryManager(RepositoryManager.getInstance());
        loadStartScreen(mainController);

        prepareStartScreen(primaryStage, mainController);
    }

    private void loadStartScreen(MainApplicationScreenController mainController) {
        mainController.loadScreen(Resource.StartWindow);
        mainController.setScreen(Resource.StartWindow);
    }

    private void prepareRepositoryManager(RepositoryManager repositoryManager) {
        repositoryManager.registerParameterRepository(RepositoryName.ACTOR_PARAMETERS, new ActorParametersRepository());
        repositoryManager.registerParameterRepository(RepositoryName.CONNECTION_PARAMETERS, new ConnectionParametersRepository());
        repositoryManager.registerParameterRepository(RepositoryName.SIMULATION_PARAMETERS, new SimulationParametersRepository());
    }

    private void prepareStartScreen(Stage primaryStage, MainApplicationScreenController mainController) {
        Group root = new Group();
        root.getChildren().addAll(mainController);

        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.rgb(33, 47, 61));

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}