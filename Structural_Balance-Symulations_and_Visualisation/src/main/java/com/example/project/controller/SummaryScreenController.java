package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.controller.parameters.ParametersScreenController;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.model.ConnectionParameters;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.ConnectionParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryScreenController implements Initializable {
    private ParametersScreenController parametersScreenController;
    private VisualisationGeneratorScreenController screenParent;

    @FXML
    private Label connectionPercentageLabel;
    @FXML
    private Label posToNegPercentageLabel;
    @FXML
    private Label rowNumberLabel;
    @FXML
    private Label columnNumberLabel;
    @FXML
    private Label stepNumberLabel;
    @FXML
    private Label annealingLabel;

    public void injectParametersScreenController(ParametersScreenController parametersScreenController) {
        this.parametersScreenController = parametersScreenController;
    }

    public void injectParentScreenController(VisualisationGeneratorScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void updateScreenValues() {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();

        updateConnectionParameters(repositoryManager);
        updateActorsParameters(repositoryManager);
        updateSimulationParameters(repositoryManager);
    }

    private void updateConnectionParameters(RepositoryManager repositoryManager) {
        ConnectionParametersRepository repository =
                (ConnectionParametersRepository) repositoryManager.getParameterRepositoryByResource(RepositoryName.CONNECTION_PARAMETERS);
        ConnectionParameters connectionParameters = repository.getConnectionParameters();

        String connectionPercentage = String.valueOf(connectionParameters.getConnectionExistencePercentage());
        String posToNegPercentage = String.valueOf(connectionParameters.getPositiveConnectionsPercentage());

        connectionPercentageLabel.setText(connectionPercentage);
        posToNegPercentageLabel.setText(posToNegPercentage);
    }

    private void updateActorsParameters(RepositoryManager repositoryManager) {
        ActorParametersRepository repository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByResource(RepositoryName.ACTOR_PARAMETERS);
        ActorParameters actorParameters = repository.getActorParameters();

        String rowNumber = String.valueOf(actorParameters.getNumberOfRows());
        String columnNumber = String.valueOf(actorParameters.getNumberOfColumns());

        rowNumberLabel.setText(rowNumber);
        columnNumberLabel.setText(columnNumber);
    }

    private void updateSimulationParameters(RepositoryManager repositoryManager) {
        SimulationParametersRepository repository =
                (SimulationParametersRepository) repositoryManager.getParameterRepositoryByResource(RepositoryName.SIMULATION_PARAMETERS);
        SimulationParameters simulationParameters = repository.getSimulationParameters();

        String stepNumber = String.valueOf(simulationParameters.getNumberOfSteps());
        String annealing = String.valueOf(simulationParameters.getAnnealingParameter());

        stepNumberLabel.setText(stepNumber);
        annealingLabel.setText(annealing);
    }

    public void generateSimulation() {
        screenParent.changeScreenToVisualisationScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        updateActorsParameters(repositoryManager);
        updateConnectionParameters(repositoryManager);
        updateSimulationParameters(repositoryManager);
    }
}
