package com.example.project.controller;

import com.example.project.controller.simulationflow.ActionTabController;
import com.example.project.controller.simulationflow.ParameterTabController;
import com.example.project.controller.simulationflow.StateTabController;
import javafx.fxml.FXML;

public class SimulationFlow2Controller implements ControlledScreen{
    @FXML
    private ActionTabController actionTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private StateTabController stateTabController;
}
