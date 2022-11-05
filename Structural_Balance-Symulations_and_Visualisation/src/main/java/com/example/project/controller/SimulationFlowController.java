package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.controller.simulationflow.*;
import com.example.project.database.model.NewSimulationParameters;
import com.example.project.database.repository.NewSimulationParametersRepository;
import com.example.project.database.repository.RepositoryManager;
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
    private final NewSimulationParametersRepository repository = (NewSimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.NEW_SIMULATION_PARAMETERS);
    @FXML
    private NetTabController netTabController;
    @FXML
    private ChartTabController chartTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private ControlPanelController controlPanelController;

    private SimulationFlow simulationFlow;


    public void nextStep() {
        getAllControllers().forEach(TabController::nextSimulationStep);
    }

    public void previousStep() {
        netTabController.previousSimulationStep();
    }

    public void skipToTheEnd() {
        netTabController.skipToEnd();
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
        NewSimulationParameters parameters = repository.getSimulationParameters();
        simulationFlow = new SimulationFlow(actorList, relationList, parameters);
        netTabController.prepareInitialVisualisation(actorList, relationList, simulationFlow);
    }

    private List<TabController> getAllControllers() {
        return List.of(this.netTabController, this.chartTabController, this.parameterTabController);
    }
}
