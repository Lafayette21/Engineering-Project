package com.example.project.controller;

import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;

public class VisualisationGeneratorScreenController implements ControlledScreen {

    MainApplicationScreenController controller;

    @FXML private ParametersScreenController parametersScreenController;
    @FXML private VisualisationScreenController visualisationScreenController;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }

    @FXML private void initialize(){

    }
}
