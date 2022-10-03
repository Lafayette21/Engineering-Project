package com.example.project.simulation;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationFlow {
    private Map<Integer, List<Relation>> simulationMap = new HashMap<>();

    private final SimulationParametersValues simulationParametersValues;
    private final List<Actor> actorList;
    private List<Relation> currentRelationList;
    private Integer currentStepNumber = 1;
    private SimulationResolver simulationResolver;

    public SimulationFlow(List<Actor> actorList, List<Relation> currentRelationList, SimulationParametersValues simulationParametersValues) {
        this.actorList = actorList;
        this.currentRelationList = currentRelationList;
        this.simulationParametersValues = simulationParametersValues;
    }

    public void nextStep(AnchorPane visualisationPanel) {
        currentStepNumber += 1;
        if (isMoreThanLastStep()) {
            showAlert("Górny próg symulacji");
        } else if (simulationMap.containsKey(currentStepNumber)) {
            currentRelationList = simulationMap.get(currentStepNumber);
        } else {
            currentRelationList = simulationResolver.getNextStepRelations(currentRelationList);
            simulationMap.put(currentStepNumber, currentRelationList);
        }
        drawToCanvas(visualisationPanel);
    }

    private boolean isMoreThanLastStep() {
        return currentStepNumber > simulationParametersValues.numberOfSteps();
    }

    public void previousStep(AnchorPane visualisationPanel) {
        if (isFirstStep()) {
            showAlert("Dolny próg symulacji");
        } else {
            currentStepNumber -= 1;
            simulationMap.get(currentStepNumber);
        }
        drawToCanvas(visualisationPanel);
    }

    private boolean isFirstStep() {
        return currentStepNumber == 1;
    }

    private void drawToCanvas(AnchorPane visualisationPanel) {
        CanvasDrawer.draw(visualisationPanel, actorList, currentRelationList);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.format("Pobieranie kroku symulacji nie powiodło się, z powodu: {}", message));
        alert.showAndWait();
    }

}
