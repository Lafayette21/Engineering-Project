package com.example.project.controller;

import com.example.project.controller.parameters.Parameters;
import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.Map;

public class VisualisationGeneratorScreenController implements ControlledScreen {

    MainApplicationScreenController controller;

    @FXML private ParametersScreenController parametersScreenController;
    @FXML private VisualisationScreenController visualisationScreenController;

    Map<Parameters,ParametersScreenController> parametersScreens = new HashMap<>();

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }
}
