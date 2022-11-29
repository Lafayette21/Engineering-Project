package com.example.project.controller.parameters;

import com.example.project.RepositoryName;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.model.ConnectionParameters;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.ConnectionParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SummaryScreen2Controller {
    @FXML
    private Label connectionPercentageLabel;
    @FXML
    private Label posToNegPercentageLabel;
    @FXML
    private Label rowNumberLabel;
    @FXML
    private Label columnNumberLabel;

    public void updateScreenValues() {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();

        updateConnectionParameters(repositoryManager);
        updateActorsParameters(repositoryManager);
    }

    private void updateConnectionParameters(RepositoryManager repositoryManager) {
        ConnectionParametersRepository repository =
                (ConnectionParametersRepository) repositoryManager.getParameterRepositoryByName(RepositoryName.CONNECTION_PARAMETERS);
        ConnectionParameters connectionParameters = repository.getConnectionParameters();

        String connectionPercentage = String.valueOf(connectionParameters.getConnectionExistencePercentage());
        String posToNegPercentage = String.valueOf(connectionParameters.getPositiveConnectionsPercentage());

        connectionPercentageLabel.setText(connectionPercentage);
        posToNegPercentageLabel.setText(posToNegPercentage);
    }

    private void updateActorsParameters(RepositoryManager repositoryManager) {
        ActorParametersRepository repository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByName(RepositoryName.ACTOR_PARAMETERS);
        ActorParameters actorParameters = repository.getActorParameters();

        String rowNumber = String.valueOf(actorParameters.getNumberOfRows());
        String columnNumber = String.valueOf(actorParameters.getNumberOfColumns());

        rowNumberLabel.setText(rowNumber);
        columnNumberLabel.setText(columnNumber);
    }
}
