package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.exception.SimulationBalanceAchievedException;
import com.example.project.simulation.SimulationFlow;
import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NetSimulationTabController implements SimulationTabController, StateControllable, Savable {
    @FXML
    private StatePanelController statePanelController;

    @FXML
    private AnchorPane visualisationPanel;

    private SimulationFlow simulationFlow;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
        CanvasDrawer.draw(visualisationPanel, simulationFlow.getActorList(), simulationFlow.getCurrentRelationList());
    }

    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {
        try {
            simulationFlow.nextStep(visualisationPanel, simulationParameters);
        } catch (SimulationBalanceAchievedException e) {
            new SimulationBalanceAlert().showAndWait();
        }
    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {
        simulationFlow.previousStep(visualisationPanel);
    }

    @Override
    public void skipToEnd(SimulationParameters simulationParameters) {
        try {
            simulationFlow.skipToEnd(visualisationPanel, simulationParameters, statePanelController);
        } catch (SimulationBalanceAchievedException e) {
            new SimulationBalanceAlert().showAndWait();
        }
    }

    @Override
    public StatePanelController getStatePanelController() {
        return statePanelController;
    }

    @Override
    public AnchorPane getVisualisationPanel() {
        return visualisationPanel;
    }
}
