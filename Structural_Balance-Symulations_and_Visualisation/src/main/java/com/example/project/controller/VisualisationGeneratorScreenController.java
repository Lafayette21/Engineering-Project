package com.example.project.controller;

import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen, Initializable {
    MainApplicationScreenController controller;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private SummaryScreenController summaryScreenController;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        summaryScreenController.injectParametersScreenController(parametersScreenController);
    }
}
