package com.example.project.controller;

import com.example.project.exception.SimulationBalanceAchievedException;
import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import com.example.project.visualisation.util.ImageSaver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class SimulationFlowScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button nextButton;
    @FXML
    private Button skipToEndButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button downloadButton;
    @FXML
    private Button imageSaveButton;

    private SimulationFlow simulationFlow;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void nextSimulationStep() {
        try {
            simulationFlow.nextStep(visualisationPanel);
        } catch (SimulationBalanceAchievedException e) {
            removeButtonsProperties();
            showSuccesAlert();
        }
    }

    public void skipToEnd() {
        try {
            simulationFlow.skipToEnd(visualisationPanel);
        } catch (SimulationBalanceAchievedException e) {
            removeButtonsProperties();
            showSuccesAlert();
        }
    }

    private void removeButtonsProperties() {
        nextButton.cancelButtonProperty();
        skipToEndButton.cancelButtonProperty();
    }

    private void showSuccesAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Siec osiągnęła balans");
        alert.setTitle("Sukces");
        alert.showAndWait();
    }

    public void previousSimulationStep() {
        simulationFlow.previousStep(visualisationPanel);
    }

    public void downloadValues() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        SimulationParametersValues simulationParametersValues = requiredValuesDTO.simulationParameters();
        simulationFlow = new SimulationFlow(actorList, relationList, simulationParametersValues);
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }

    public void saveImage() {
        ImageSaver.save(visualisationPanel, simulationFlow.getCurrentStepNumber());
    }
}
