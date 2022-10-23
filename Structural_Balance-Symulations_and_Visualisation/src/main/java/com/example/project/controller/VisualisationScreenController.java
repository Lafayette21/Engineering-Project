package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.visualisation.screen.VisualisationGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualisationScreenController implements ControlledScreen, Initializable {
    private MainApplicationScreenController screenParent;
    private RepositoryManager repositoryManager;
    private VisualisationGenerator visualisationGenerator;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;
    @FXML
    private Button startButton;
    @FXML
    public Button returnButton;

    public void generateVisualisation() {
        repositoryManager = RepositoryManager.getInstance();
        visualisationGenerator = new VisualisationGenerator(visualisationPanel);
        visualisationGenerator.generate(repositoryManager);
    }

    private ParametersValueHandler getParametersValueHandler() {
        return (ParametersValueHandler) screenParent.getUserData();
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
        screenParent.setScreen(Resource.SimulationFlow);
    }

    private void sendSimulationRequiredParameters() {
        screenParent.setUserData(getSimulationRequiredValuesDTO());
    }

    private SimulationRequiredValuesDTO getSimulationRequiredValuesDTO() {
        List<Actor> actorList = visualisationGenerator.getActorList();
        List<Relation> relationList = visualisationGenerator.getRelationList();
        SimulationParametersRepository simulationParametersRepository =
                (SimulationParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.SimulationParameters);
        SimulationParameters simulationParameters = simulationParametersRepository.getSimulationParameters();
        return new SimulationRequiredValuesDTO(actorList, relationList, simulationParameters);
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void setRepositoryManager(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
