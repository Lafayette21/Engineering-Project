package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import com.example.project.dto.ParameterValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen {
    MainApplicationScreenController controller;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private VisualisationScreenController visualisationScreenController;

    private Map<Resource, ParameterValue> parameterValueMap = new HashMap<>();

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }

}
