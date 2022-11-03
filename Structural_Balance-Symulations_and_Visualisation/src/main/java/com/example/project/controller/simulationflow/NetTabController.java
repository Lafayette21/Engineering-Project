package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.exception.SimulationBalanceAchievedException;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class NetTabController {
    @FXML
    private StatePanelController statePanelController;

    @FXML
    private AnchorPane visualisationPanel;

    private SimulationFlow simulationFlow;

    public void prepareInitialVisualisation(SimulationRequiredValuesDTO requiredValues) {
        List<Actor> actorList = requiredValues.actorList();
        List<Relation> relationList = requiredValues.relationList();
        SimulationParameters parameters = requiredValues.simulationParameters();
        simulationFlow = new SimulationFlow(actorList, relationList, parameters);
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }

    public void nextSimulationStep() {
        updateCurrentStepNumber();
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
        updateCurrentStepNumber();
        simulationFlow.previousStep(visualisationPanel);
    }

    private void updateCurrentStepNumber() {
        statePanelController.updateStepNumber(simulationFlow.getCurrentStepNumber());
    }
}
