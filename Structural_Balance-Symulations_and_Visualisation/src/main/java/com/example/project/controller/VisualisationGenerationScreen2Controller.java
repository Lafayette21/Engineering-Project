package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ActorsParametersScreenController;
import com.example.project.controller.parameters.ConnectionParametersScreenController;
import com.example.project.controller.parameters.ParameterScreen;
import com.example.project.controller.parameters.SummaryScreen2Controller;
import com.google.common.collect.ImmutableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
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

    public void updateConnectionPercentageLabel(int updatedValue) {
        summaryScreen2Controller.setConnectionPercentageLabelValue(updatedValue);
    }

    public void updatePositivePercentageLabel(int updatedValue) {
        summaryScreen2Controller.setPositivePercentageLabelValue(updatedValue);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getParameterScreens().forEach(parameterScreen -> parameterScreen.injectScreenParent(this));
    }

    private List<ParameterScreen> getParameterScreens() {
        return ImmutableList.of(actorsParametersScreenController, connectionParametersScreenController, summaryScreen2Controller);
    }
}
