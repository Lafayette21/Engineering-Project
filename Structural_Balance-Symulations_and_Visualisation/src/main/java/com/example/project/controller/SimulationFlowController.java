package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.controller.simulationflow.*;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationFlowController implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
    private final SimulationParametersRepository repository = (SimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);
    @FXML
    private NetSimulationTabController netTabController;
    @FXML
    private ChartSimulationTabController chartTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private ControlPanelController controlPanelController;

    private SimulationFlow simulationFlow;


    public void nextStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getAllControllers().forEach(simulationTabController -> simulationTabController.nextSimulationStep(simulationParameters));
        updateState();
    }

    public void previousStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        netTabController.previousSimulationStep(simulationParameters);
        updateState();
    }

    public void skipToTheEnd() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        netTabController.skipToEnd(simulationParameters);
        updateState();
    }

    private void updateState() {
        getAllStateControllableControllers()
                .stream()
                .map(StateControllable::getStatePanelController)
                .forEach(statePanelController -> statePanelController.updateStateOfSimulation(simulationFlow));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareInitialSimulationVisualisation();
        controlPanelController.injectSimulationFlowController(this);
    }

    private void prepareInitialSimulationVisualisation() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        SimulationParameters parameters = repository.getSimulationParameters();
        simulationFlow = new SimulationFlow(actorList, relationList, parameters);

        getAllControllers().forEach(simulationTabController -> simulationTabController.prepareInitial(simulationFlow));
    }

    private List<SimulationTabController> getAllControllers() {
        return List.of(this.netTabController, this.chartTabController);
    }

    private List<StateControllable> getAllStateControllableControllers(){
        return List.of(this.netTabController, this.chartTabController, this.parameterTabController);
    }
}
