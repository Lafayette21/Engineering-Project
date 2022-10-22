package com.example.project;

import com.example.project.controller.MainApplicationScreenController;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.ConnectionParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainApplicationScreenController mainController = new MainApplicationScreenController();
        prepareRepositoryManager(mainController.getRepositoryManager());
        loadScreens(mainController);

        prepareStartScreen(primaryStage, mainController);
    }

    private void loadScreens(MainApplicationScreenController mainController) {
        mainController.loadScreen(Resource.StartWindow);
        mainController.loadScreen(Resource.VisualisationGenerator);
        mainController.loadScreen(Resource.Visualisation);
        mainController.loadScreen(Resource.SimulationFlow);

        mainController.setScreen(Resource.StartWindow);
    }

    private void prepareRepositoryManager(RepositoryManager repositoryManager) {
        repositoryManager.registerParameterRepository(Resource.ActorParameters, new ActorParametersRepository());
        repositoryManager.registerParameterRepository(Resource.ConnectionParameters, new ConnectionParametersRepository());
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