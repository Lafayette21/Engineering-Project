package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.*;
import com.example.project.visualisation.model.Relation;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartScreenController implements SimulationTabController{
    @FXML
    private LineChart<String, Double> chart;

    XYChart.Series<String, Double> energySeries = new XYChart.Series<>();
    XYChart.Series<String, Double> averageSeries = new XYChart.Series<>();

    private SimulationFlow simulationFlow;
    private Timeline timeline;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
    }

    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {
        int numberOfActors = simulationFlow.getActorList().size();
        List<Relation> relationList = simulationFlow.getCurrentRelationList();
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, numberOfActors);
        RelationMatrix relationMatrix = new RelationMatrix(relationList, numberOfActors);

        addDataToChart(connectionMatrix, relationMatrix);
    }

    private void addDataToChart(ConnectionMatrix connectionMatrix, RelationMatrix relationMatrix) {
        double energy = EnergyCalculator.calculate(connectionMatrix, relationMatrix);
        addEnergySeriesToChart(energy);

        double average = AverageCalculator.calculate(connectionMatrix, relationMatrix);
        addAverageSeriesToChart(average);
    }

    private void addAverageSeriesToChart(double average) {
        Integer stepNumber = simulationFlow.getCurrentStepNumber();
        averageSeries.getData().add(new XYChart.Data<>(stepNumber.toString(), average));
        chart.getData().add(averageSeries);
    }

    private void addEnergySeriesToChart(double energy) {
        Integer stepNumber = simulationFlow.getCurrentStepNumber();
        energySeries.getData().add(new XYChart.Data<>(stepNumber.toString(), energy));
        chart.getData().add(energySeries);
    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {

    }

    @Override
    public void start(SimulationParameters simulationParameters) {

    }

    @Override
    public void pause() {

    }
}
