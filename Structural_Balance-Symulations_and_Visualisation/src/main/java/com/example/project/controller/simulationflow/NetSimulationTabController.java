package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.SimulationFlow;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NetSimulationTabController implements SimulationTabController, StateControllable, Savable {
    @FXML
    private StatePanelController statePanelController;

    @FXML
    private AnchorPane visualisationPanel;

    private SimulationFlow simulationFlow;

    private Timeline timeline;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
        CanvasDrawer.draw(visualisationPanel, simulationFlow.getActorList(), simulationFlow.getCurrentRelationList(), true);
    }

    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {
        simulationFlow.nextStep(visualisationPanel, simulationParameters);

    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {
        simulationFlow.previousStep(visualisationPanel);
    }

    @Override
    public void start(SimulationParameters simulationParameters) {
        simulationFlow.startExecution(visualisationPanel, simulationParameters);
    }

    @Override
    public void pause() {
        simulationFlow.pauseExecution();
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
