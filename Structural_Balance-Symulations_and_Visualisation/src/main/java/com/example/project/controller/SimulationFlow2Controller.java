package com.example.project.controller;

import com.example.project.controller.simulationflow.ChartTabController;
import com.example.project.controller.simulationflow.ControlPanelController;
import com.example.project.controller.simulationflow.NetTabController;
import com.example.project.controller.simulationflow.ParameterTabController;
import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationFlow2Controller implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();

    @FXML
    private NetTabController netTabController;
    @FXML
    private ChartTabController chartTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private ControlPanelController controlPanelController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        SimulationParameters parameters = requiredValuesDTO.simulationParameters();
        netTabController.prepareInitialVisualisation(actorList, relationList, parameters);
    }
}
