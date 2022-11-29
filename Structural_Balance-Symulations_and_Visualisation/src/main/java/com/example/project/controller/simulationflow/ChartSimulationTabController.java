package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.*;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChartSimulationTabController implements SimulationTabController, StateControllable, Initializable, Savable {
    @FXML
    private StatePanelController statePanelController;
    @FXML
    public AnchorPane visualisationPanel;
    @FXML
    private LineChart<String, Double> stepToEnergyChart;

    XYChart.Series<String, Double> energySeries = new XYChart.Series<>();
    XYChart.Series<String, Double> averageSeries = new XYChart.Series<>();

    private SimulationFlow simulationFlow;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
    }

    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {
        Integer stepNumber = simulationFlow.getCurrentStepNumber();
        int numberOfActors = simulationFlow.getActorList().size();
        List<Relation> relationList = simulationFlow.getCurrentRelationList();
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, numberOfActors);
        RelationMatrix relationMatrix = new RelationMatrix(relationList, numberOfActors);

        double energy = EnergyCalculator.calculate(connectionMatrix, relationMatrix);
        addEnergySeriesToChart(stepNumber, energy);

        double average = AverageCalculator.calculate(connectionMatrix, relationMatrix);
        addAverageSeriesToChart(stepNumber, average);
    }

    private void addAverageSeriesToChart(Integer stepNumber, double average) {
        averageSeries.getData().add(new XYChart.Data<>(stepNumber.toString(), average));
        stepToEnergyChart.getData().add(averageSeries);
    }

    private void addEnergySeriesToChart(Integer stepNumber, double energy) {
        energySeries.getData().add(new XYChart.Data<>(stepNumber.toString(), energy));
        stepToEnergyChart.getData().add(energySeries);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        energySeries.setName("Energia");
        averageSeries.setName("Średnia");
    }

    @Override
    public AnchorPane getVisualisationPanel() {
        return visualisationPanel;
    }
}
