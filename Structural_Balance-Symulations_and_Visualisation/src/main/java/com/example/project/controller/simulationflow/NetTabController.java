package com.example.project.controller.simulationflow;

import com.example.project.RepositoryName;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.exception.SimulationBalanceAchievedException;
import com.example.project.simulation.SimulationFlow;
import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class NetTabController implements TabController, StateControllable {
    private final SimulationParametersRepository repository = (SimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);

    @FXML
    private StatePanelController statePanelController;

    @FXML
    private AnchorPane visualisationPanel;

    private SimulationFlow simulationFlow;

    public void prepareInitialVisualisation(List<Actor> actorList, List<Relation> relationList, SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
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
            simulationFlow.skipToEnd(visualisationPanel, simulationParameters);
        } catch (SimulationBalanceAchievedException e) {
            new SimulationBalanceAlert().showAndWait();
        }
    }

    @Override
    public StatePanelController getStatePanelController() {
        return statePanelController;
    }
}
