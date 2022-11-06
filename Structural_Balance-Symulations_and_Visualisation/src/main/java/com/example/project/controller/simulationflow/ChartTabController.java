package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class ChartTabController implements TabController, StateControllable {
    @FXML
    public LineChart<Number, Number> stepToEnergyChart;

    @FXML
    private StatePanelController statePanelController;


    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {

    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {

    }

    @Override
    public void skipToEnd(SimulationParameters simulationParameters) {

    }

    @Override
    public StatePanelController getStatePanelController() {
        return statePanelController;
    }
}
