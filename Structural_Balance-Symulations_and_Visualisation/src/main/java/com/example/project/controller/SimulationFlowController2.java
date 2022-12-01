package com.example.project.controller;

import com.example.project.controller.simulationflow.ChartScreenController;
import com.example.project.controller.simulationflow.ControlScreenController;
import com.example.project.controller.simulationflow.NetScreenController;
import com.example.project.controller.simulationflow.StatePanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationFlowController2 implements ControlledScreen, Initializable {
    @FXML
    private NetScreenController netScreenController;
    @FXML
    private ChartScreenController chartScreenController;
    @FXML
    private StatePanelController statePanelController;
    @FXML
    private ControlScreenController controlScreenController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println();
    }
}
