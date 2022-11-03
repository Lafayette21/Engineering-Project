package com.example.project.controller;

import com.example.project.controller.ControlledScreen;
import com.example.project.controller.simulationflow.ChartTabController;
import com.example.project.controller.simulationflow.ControlPanelController;
import com.example.project.controller.simulationflow.NetTabController;
import com.example.project.controller.simulationflow.ParameterTabController;
import javafx.fxml.FXML;

public class SimulationFlow2Controller implements ControlledScreen {
    @FXML
    private NetTabController netTabController;
    @FXML
    private ChartTabController chartTabController;
    @FXML
    private ParameterTabController parameterTabController;
    @FXML
    private ControlPanelController controlPanelController;

}
