package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.*;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.List;

public class StatePanelController implements SimulationTabController{
    @FXML
    public Label stepNumberLabel;
    @FXML
    public Label averageLabel;
    @FXML
    public Label energyLabel;

    private SimulationFlow simulationFlow;
    private Timeline timeline;

    @Override
    public void prepareInitial(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
        updateStateOfSimulation(simulationFlow);
    }

    @Override
    public void nextSimulationStep(SimulationParameters simulationParameters) {
        updateStateOfSimulation(simulationFlow);
    }

    @Override
    public void previousSimulationStep(SimulationParameters simulationParameters) {
        updateStateOfSimulation(simulationFlow);
    }

    @Override
    public void start(SimulationParameters simulationParameters) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(simulationParameters.getTime()),
                event -> nextSimulationStep(simulationParameters));
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void pause() {
        timeline.stop();
    }

    public void updateStateOfSimulation(SimulationFlow simulationFlow) {
        Integer stepNumber = simulationFlow.getCurrentStepNumber();
        List<Relation> relationList = simulationFlow.getCurrentRelationList();
        List<Actor> actorList = simulationFlow.getActorList();

        RelationMatrix relationMatrix = new RelationMatrix(relationList, actorList.size());
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, actorList.size());

        updateStepNumber(stepNumber);
        updateAverage(relationMatrix, connectionMatrix);
        updateEnergy(relationMatrix, connectionMatrix);
    }

    private void updateStepNumber(Integer stepNumber) {
        stepNumberLabel.setText(String.valueOf(stepNumber));
    }

    private void updateAverage(RelationMatrix relationMatrix, ConnectionMatrix connectionMatrix) {
        double average = AverageCalculator.calculate(connectionMatrix, relationMatrix);
        averageLabel.setText(String.format("%.2f", average));
    }

    private void updateEnergy(RelationMatrix relationMatrix, ConnectionMatrix connectionMatrix) {
        double energy = EnergyCalculator.calculate(connectionMatrix, relationMatrix);
        energyLabel.setText(String.format("%.2f", energy));
    }
}
