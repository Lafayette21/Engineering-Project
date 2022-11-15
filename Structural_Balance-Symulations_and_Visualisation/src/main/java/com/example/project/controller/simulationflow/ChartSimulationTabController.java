package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.*;
import com.example.project.visualisation.model.Relation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    private Timeline timeline;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {
        int size = energySeries.getData().size();
        energySeries.getData().remove(size - 1);
        stepToEnergyChart.getData().remove(size - 1);
    }

    @Override
    public void start(SimulationParameters simulationParameters) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(simulationParameters.getTime()),
                event -> {
            nextSimulationStep(simulationParameters);
            statePanelController.updateStateOfSimulation(simulationFlow);
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
        stepToEnergyChart.getData().add(averageSeries);
    }

    private void addEnergySeriesToChart(double energy) {
        Integer stepNumber = simulationFlow.getCurrentStepNumber();
        energySeries.getData().add(new XYChart.Data<>(stepNumber.toString(), energy));
        stepToEnergyChart.getData().add(energySeries);
    }

    @Override
    public void pause() {
        timeline.stop();
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
