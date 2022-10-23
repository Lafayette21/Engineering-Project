package com.example.project.controller;

import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.database.model.SimulationParameters;
import com.example.project.exception.SimulationBalanceAchievedException;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.util.ImageSaver;
import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
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
            new SimulationBalanceAlert().showAndWait();
        }
    }

    public void skipToEnd() {
        try {
            simulationFlow.skipToEnd(visualisationPanel);
        } catch (SimulationBalanceAchievedException e) {
            new SimulationBalanceAlert().showAndWait();
        }
    }

    public void previousSimulationStep() {
        simulationFlow.previousStep(visualisationPanel);
    }

    public void downloadValues() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        SimulationParameters simulationParametersValues = requiredValuesDTO.simulationParameters();
        simulationFlow = new SimulationFlow(actorList, relationList, simulationParametersValues);
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }

    public void saveImage() {
        ImageSaver.save(visualisationPanel, simulationFlow.getCurrentStepNumber());
    }
}
