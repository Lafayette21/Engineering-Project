package com.example.project.controller;

import com.example.project.controller.simulationflow.ChartTabController;
import com.example.project.controller.simulationflow.ControlPanelController;
import com.example.project.controller.simulationflow.NetTabController;
import com.example.project.controller.simulationflow.ParameterTabController;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationFlowController implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();

    @FXML
    private NetTabController netTabController;
    @FXML
    private ChartTabController chartTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private ControlPanelController controlPanelController;

    public void nextStep(){
        netTabController.nextSimulationStep();
    }

    public void previousStep(){
        netTabController.previousSimulationStep();
    }

    public void skipToTheEnd(){
        netTabController.skipToEnd();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareInitialSimulationVisualisation();
        controlPanelController.injectSimulationFlowController(this);
    }

    private void prepareInitialSimulationVisualisation() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        netTabController.prepareInitialVisualisation(requiredValuesDTO);
    }
}
