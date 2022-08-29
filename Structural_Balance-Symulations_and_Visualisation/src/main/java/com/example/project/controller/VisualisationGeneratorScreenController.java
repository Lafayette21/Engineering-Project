package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen, Initializable {
    MainApplicationScreenController screenController;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private SummaryScreenController summaryScreenController;

    public void changeScreenToVisualisationScreen(){
        screenController.setScreen(Resource.Visualisation);
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        screenController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        summaryScreenController.injectParametersScreenController(parametersScreenController);
        summaryScreenController.injectParentScreenController(this);
    }
}
