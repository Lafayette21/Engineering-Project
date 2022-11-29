package com.example.project.controller;

import com.example.project.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController implements ControlledScreen {
    private final MainApplicationScreenController screenController = MainApplicationScreenController.getInstance();

    @FXML
    private Button startButton;

    public void changeScreenToVisualisationGenerator() {
        screenController.loadScreen(Resource.VisualisationGenerator2);
        screenController.setScreen(Resource.VisualisationGenerator2);
    }
}
