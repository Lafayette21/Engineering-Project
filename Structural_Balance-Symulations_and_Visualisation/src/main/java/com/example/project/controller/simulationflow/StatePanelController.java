package com.example.project.controller.simulationflow;

import com.example.project.simulation.*;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class StatePanelController {
    @FXML
    public Label stepNumberLabel;
    @FXML
    public Label averageLabel;
    @FXML
    public Label energyLabel;

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
