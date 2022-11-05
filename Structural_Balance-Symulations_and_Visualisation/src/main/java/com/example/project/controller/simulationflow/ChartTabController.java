package com.example.project.controller.simulationflow;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class ChartTabController implements TabController {
    @FXML
    public LineChart<Number, Number> stepToEnergyChart;

    @FXML
    private StatePanelController statePanelController;


    @Override
    public void nextSimulationStep() {

    }

    @Override
    public void previousSimulationStep() {

    }

    @Override
    public void skipToEnd() {

    }
}
