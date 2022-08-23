package com.example.project.controller;

import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen {
    MainApplicationScreenController controller;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private SummaryScreenController summaryScreenController;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }

}
