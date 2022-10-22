package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.ParameterRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.parametervalues.SimulationParametersValues;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        ParametersValueHandler valueHandler = parametersScreenController.getParametersValueHandler();
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        try {
            updateConnectionParameters(valueHandler);
            updateActorsParameters(repositoryManager);
            updateSimulationParameters(valueHandler);
        } catch (NullPointerException e) {
            showAlert();
        }
    }

    private void updateConnectionParameters(ParametersValueHandler valueHandler) {
        ConnectionsParametersValues parametersValues =
                (ConnectionsParametersValues) valueHandler.getParameterValueByResource(Resource.ConnectionParameters);

        String connectionPercentage = String.valueOf(parametersValues.connectionCreationPercentRatio());
        String posToNegPercentage = String.valueOf(parametersValues.positiveToNegativePercentRatio());

        connectionPercentageLabel.setText(connectionPercentage);
        posToNegPercentageLabel.setText(posToNegPercentage);
    }

    private void updateActorsParameters(RepositoryManager repositoryManager) {
        ActorParametersRepository repository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.ActorParameters);
        ActorParameters actorParameters = repository.getActorParameters();

        String rowNumber = String.valueOf(actorParameters.getNumberOfRows());
        String columnNumber = String.valueOf(actorParameters.getNumberOfColumns());

        rowNumberLabel.setText(rowNumber);
        columnNumberLabel.setText(columnNumber);
    }

    private void updateSimulationParameters(ParametersValueHandler valueHandler) {
        SimulationParametersValues parameterValue =
                (SimulationParametersValues) valueHandler.getParameterValueByResource(Resource.SimulationParameters);

        String stepNumber = String.valueOf(parameterValue.numberOfSteps());
        String annealing = String.valueOf(parameterValue.annealingValue());

        stepNumberLabel.setText(stepNumber);
        annealingLabel.setText(annealing);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Część z parametrów nie była jeszcze ustawiona. Ustaw je aby móc zobaczyć podsumowanie");
        alert.showAndWait();
    }

    public void generateSimulation() {
        screenParent.changeScreenToVisualisationScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        ActorParametersRepository actorParametersRepository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.ActorParameters);
        ActorParameters actorParameters = actorParametersRepository.getActorParameters();
        rowNumberLabel.setText(String.valueOf(actorParameters.getNumberOfRows()));
    }
}
