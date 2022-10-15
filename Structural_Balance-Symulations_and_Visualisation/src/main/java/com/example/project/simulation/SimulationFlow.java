package com.example.project.simulation;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class SimulationFlow {
    private static final String UPPER_LIMIT_HIT_MESSAGE = "Osiągnięto górny próg symulacji";
    private static final String LOWER_LIMIT_HIT_MESSAGE = "Osiągnięto dolny próg symulacji";

    private final Map<Integer, List<Relation>> simulationMap = new HashMap<>();

    private final SimulationParametersValues simulationParametersValues;
    private final List<Actor> actorList;
    private List<Relation> currentRelationList;
    private Integer currentStepNumber = 1;
    private SimulationResolver simulationResolver;

    public SimulationFlow(List<Actor> actorList, List<Relation> currentRelationList, SimulationParametersValues simulationParametersValues) {
        this.actorList = actorList;
        this.currentRelationList = currentRelationList;
        this.simulationParametersValues = simulationParametersValues;
        setSimulationResolver(actorList, simulationParametersValues);
    }

    private void setSimulationResolver(List<Actor> actorList, SimulationParametersValues simulationParametersValues) {
        double annealingParameter = simulationParametersValues.annealingValue();
        int numberOfActors = actorList.size();
        simulationResolver = new SimulationResolver(annealingParameter, numberOfActors);
    }

    public void nextStep(AnchorPane visualisationPanel) {
        currentStepNumber += 1;
        if (isMoreThanLastStep()) {
            showAlert(UPPER_LIMIT_HIT_MESSAGE);
        } else {
            moveToNextStep();
            drawToCanvas(visualisationPanel);
        }
    }

    private boolean isMoreThanLastStep() {
        return currentStepNumber > simulationParametersValues.numberOfSteps();
    }

    public void skipToEnd(AnchorPane visualisationPanel) {
        IntStream.range(0, simulationParametersValues.numberOfSteps() + 1)
                .forEach(step -> moveToNextStep());
        drawToCanvas(visualisationPanel);
    }

    private void moveToNextStep() {
        if (simulationMap.containsKey(currentStepNumber)) {
            currentRelationList = simulationMap.get(currentStepNumber);
        } else {
            currentRelationList = simulationResolver.getNextStepRelations(currentRelationList);
            simulationMap.put(currentStepNumber, currentRelationList);
        }
    }

    public void previousStep(AnchorPane visualisationPanel) {
        if (isFirstStep()) {
            showAlert(LOWER_LIMIT_HIT_MESSAGE);
        } else {
            currentStepNumber -= 1;
            currentRelationList = simulationMap.get(currentStepNumber);
            drawToCanvas(visualisationPanel);
        }
    }

    private boolean isFirstStep() {
        return currentStepNumber == 1;
    }

    private void drawToCanvas(AnchorPane visualisationPanel) {
        CanvasDrawer.draw(visualisationPanel, actorList, currentRelationList);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.format("Pobieranie kroku symulacji nie powiodło się, z powodu: %s", message));
        alert.showAndWait();
    }

    public Integer getCurrentStepNumber() {
        return currentStepNumber;
    }
}
