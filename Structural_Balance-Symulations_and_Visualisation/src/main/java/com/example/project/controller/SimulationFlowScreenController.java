package com.example.project.controller;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class SimulationFlowScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button downloadButton;

    private SimulationFlow simulationFlow;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void nextSimulationStep() {
        simulationFlow.nextStep(visualisationPanel);
    }

    public void skipToEnd() {
        simulationFlow.skipToEnd(visualisationPanel);
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
    }
}
