package com.example.project.controller;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationFlowScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;

    private final Map<Integer, List<Relation>> simulationMap = new HashMap<>();

    @FXML
    private AnchorPane visualisationPanel;

    private Integer currentNumber = 1;
    private List<Actor> actorList;
    private List<Relation> currentRelationList;
    private SimulationParametersValues simulationParametersValues;

    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button downloadButton;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void nextSimulationStep() {
        currentNumber += 1;
        if (simulationMap.containsKey(currentNumber)) {
            currentRelationList = simulationMap.get(currentNumber);
        } else {
        }
    }

    public void previousSimulationStep() {
        currentNumber -= 1;
    }

    public void downloadValues() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        actorList = requiredValuesDTO.actorList();
        currentRelationList = requiredValuesDTO.relationList();
        simulationParametersValues = requiredValuesDTO.simulationParameters();

        simulationMap.put(currentNumber, currentRelationList);

        CanvasDrawer.draw(visualisationPanel, actorList, currentRelationList);
    }
}
