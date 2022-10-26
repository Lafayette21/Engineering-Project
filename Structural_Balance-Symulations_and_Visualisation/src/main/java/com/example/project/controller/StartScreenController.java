package com.example.project.controller;

import com.example.project.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController implements ControlledScreen {
    private MainApplicationScreenController screenController = MainApplicationScreenController.getInstance();

    @FXML private Button startButton;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        screenController = screenParent;
    }

    public void changeScreenToVisualisationGenerator() {
        screenController.loadScreen(Resource.VisualisationGenerator);
        screenController.setScreen(Resource.VisualisationGenerator);
    }
}
