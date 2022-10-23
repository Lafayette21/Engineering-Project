package com.example.project.controller;

import com.example.project.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController implements ControlledScreen {
    private MainApplicationScreenController parentController;

    @FXML private Button startButton;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        parentController = screenParent;
    }

    public void changeScreenToVisualisationGenerator() {
        parentController.setScreen(Resource.VisualisationGenerator);
    }
}
