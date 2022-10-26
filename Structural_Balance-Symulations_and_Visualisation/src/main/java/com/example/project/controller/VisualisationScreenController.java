package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.Resource;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.screen.VisualisationGenerator;
import com.example.project.visualisation.util.SimulationRequiredParametersHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualisationScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
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
        screenParent.setUserData(getSimulationRequiredValuesDTO());
    }

    private SimulationRequiredValuesDTO getSimulationRequiredValuesDTO() {
        List<Actor> actorList = simulationRequiredParametersHandler.getActorList();
        List<Relation> relationList = simulationRequiredParametersHandler.getRelationList();
        SimulationParametersRepository simulationParametersRepository =
                (SimulationParametersRepository) repositoryManager.getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);
        SimulationParameters simulationParameters = simulationParametersRepository.getSimulationParameters();
        return new SimulationRequiredValuesDTO(actorList, relationList, simulationParameters);
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
