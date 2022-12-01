package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.controller.simulationflow.*;
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

public class SimulationFlowController2 implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
    private final SimulationParametersRepository repository = (SimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);

    @FXML
    private NetScreenController netScreenController;
    @FXML
    private ChartScreenController chartScreenController;
    @FXML
    private StatePanelController statePanelController;
    @FXML
    private ControlScreenController controlScreenController;
    @FXML
    private ParameterScreenController parameterScreenController;

    private SimulationFlow simulationFlow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        simulationFlow = new SimulationFlow(actorList, relationList);

        netScreenController.prepareInitial(simulationFlow);
    }
}
