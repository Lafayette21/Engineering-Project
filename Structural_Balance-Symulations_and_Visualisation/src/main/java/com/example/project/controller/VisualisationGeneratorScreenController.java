package com.example.project.controller;

import javafx.fxml.FXML;

public class VisualisationGeneratorScreenController implements ControlledScreen {

    ScreenController controller;

    @FXML private ParametersScreenController parametersScreenController;
    @FXML private VisualisationScreenController visualisationScreenController;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    @FXML private void initialize(){

    }
}
