package com.example.project.simulation;

import com.example.project.controller.simulationflow.StatePanelController;
import com.example.project.database.model.SimulationParameters;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationFlow {
    private final Map<Integer, List<Relation>> simulationMap = new HashMap<>();

    private final List<Actor> actorList;
    private List<Relation> currentRelationList;
    private Integer currentStepNumber = 0;
    private Timeline timeline;

    public SimulationFlow(List<Actor> actorList, List<Relation> currentRelationList) {
        this.actorList = actorList;
        this.currentRelationList = currentRelationList;
        simulationMap.put(currentStepNumber, currentRelationList);
    }

    public void nextStep(AnchorPane visualisationPanel, SimulationParameters simulationParameters) {
        SimulationResolver simulationResolver = getSimulationResolver(simulationParameters);
        currentStepNumber += 1;
        moveToNextStep(simulationResolver);
        drawToCanvas(visualisationPanel);
    }

    private SimulationResolver getSimulationResolver(SimulationParameters simulationParameters) {
        double temperature = simulationParameters.getTemperature();
        int numberOfActors = actorList.size();
        return new SimulationResolver(temperature, numberOfActors);
    }

    public void startExecution(AnchorPane visualisationPanel, SimulationParameters simulationParameters, StatePanelController statePanelController) {
        SimulationResolver simulationResolver = getSimulationResolver(simulationParameters);
        Timeline timeline = createTimeline(visualisationPanel, simulationParameters, statePanelController, simulationResolver);
        timeline.play();
    }

    private Timeline createTimeline(AnchorPane visualisationPanel, SimulationParameters simulationParameters, StatePanelController statePanelController, SimulationResolver simulationResolver) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(simulationParameters.getTime()),
                event -> {
                    currentStepNumber += 1;
                    moveToNextStep(simulationResolver);
                    statePanelController.updateStateOfSimulation(this);
                    drawToCanvas(visualisationPanel);
                });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }

    public void pauseExecution(){
        timeline.stop();
    }

    private void moveToNextStep(SimulationResolver simulationResolver) {
        if (simulationMap.containsKey(currentStepNumber)) {
            currentRelationList = simulationMap.get(currentStepNumber);
        } else {
            currentRelationList = simulationResolver.getNextStepRelations(currentRelationList);
            simulationMap.put(currentStepNumber, currentRelationList);
        }
    }

    public void previousStep(AnchorPane visualisationPanel) {
        if (isFirstStep()) {
            showLowerBoundHitAlert();
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
        CanvasDrawer.draw(visualisationPanel, actorList, currentRelationList, true);
    }

    private void showLowerBoundHitAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Pobieranie kroku symulacji nie powiodło się, z powodu: Osiągnięcia dolnego progu symulacji");
        alert.showAndWait();
    }

    public Integer getCurrentStepNumber() {
        return currentStepNumber;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public List<Relation> getCurrentRelationList() {
        return currentRelationList;
    }
}
