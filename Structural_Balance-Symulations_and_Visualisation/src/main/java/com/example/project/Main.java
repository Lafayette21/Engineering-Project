package com.example.project;

import com.example.project.startwindow.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartWindow.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}