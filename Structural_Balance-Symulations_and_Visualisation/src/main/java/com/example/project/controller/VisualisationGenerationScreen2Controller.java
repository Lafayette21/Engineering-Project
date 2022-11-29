package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ActorsParametersScreenController;
import com.example.project.controller.parameters.ConnectionParametersScreenController;
import com.example.project.controller.parameters.SummaryScreen2Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationGenerationScreen2Controller implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenController = MainApplicationScreenController.getInstance();

    @FXML
    private ActorsParametersScreenController actorsParametersScreenController;
    @FXML
    private ConnectionParametersScreenController connectionParametersScreenController;
    @FXML
    private SummaryScreen2Controller summaryScreen2Controller;

    public void changeScreenToVisualisationScreen() {
        screenController.loadScreen(Resource.Visualisation);
        screenController.setScreen(Resource.Visualisation);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        summaryScreen2Controller.injectScreenParent(this);
    }
}
