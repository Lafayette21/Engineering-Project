package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ActorsParametersScreenController;
import com.example.project.controller.parameters.ConnectionParametersScreenController;
import com.example.project.controller.parameters.SummaryScreen2Controller;
import javafx.fxml.FXML;

public class VisualisationGenerationScreen2Controller implements ControlledScreen{
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
}
