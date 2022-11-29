package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.VisualisationGenerator;
import com.example.project.visualisation.util.SimulationRequiredParametersHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class VisualisationScreenController implements ControlledScreen {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
    private final RepositoryManager repositoryManager = RepositoryManager.getInstance();
    private SimulationRequiredParametersHandler simulationRequiredParametersHandler;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;
    @FXML
    private Button startButton;
    @FXML
    public Button returnButton;

    public void generateVisualisation() {
        this.simulationRequiredParametersHandler = new SimulationRequiredParametersHandler(visualisationPanel);
        VisualisationGenerator.generate(simulationRequiredParametersHandler, visualisationPanel);
    }

    public void changeScreenToVisualisationGeneratorScreen() {
        clearVisualisationPanel();
        screenParent.setScreen(Resource.VisualisationGenerator);
    }

    private void clearVisualisationPanel() {
        visualisationPanel.getChildren().clear();
    }

    public void changeToSimulationFlowScreen() {
        clearVisualisationPanel();
        sendSimulationRequiredParameters();
        screenParent.loadScreen(Resource.SimulationFlow);
        screenParent.setScreen(Resource.SimulationFlow);
    }

    private void sendSimulationRequiredParameters() {
        try {
            screenParent.setUserData(getSimulationRequiredValuesDTO());
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Najpierw musisz stworzyć symulację!");
            alert.showAndWait();
        }
    }

    private SimulationRequiredValuesDTO getSimulationRequiredValuesDTO() {
        List<Actor> actorList = simulationRequiredParametersHandler.getActorList();
        List<Relation> relationList = simulationRequiredParametersHandler.getRelationList();
        return new SimulationRequiredValuesDTO(actorList, relationList);
    }
}
