package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.database.model.ConnectionParameters;
import com.example.project.database.repository.ConnectionParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.parametervalues.ConnectionsParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionParametersScreenController implements ParameterControlledScreen, Initializable {
    private static final int MIN_SPINNER_VALUE = 0;
    private static final int MAX_SPINNER_VALUE = 100;
    private static final int INITIAL_SPINNER_VALUE = 50;
    private static final int STEP_SPINNER_VALUE = 5;

    @FXML
    private Spinner<Integer> connectionExistenceSpinner;
    @FXML
    private Spinner<Integer> positiveRatioSpinner;
    @FXML
    private Button updateButton;

    private ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void updateParametersValues() {
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        ConnectionsParametersValues connectionsParametersValues =
                new ConnectionsParametersValues(connectionExistenceSpinner.getValue(), positiveRatioSpinner.getValue());
        valueHandler.updateValues(Resource.ConnectionParameters, connectionsParametersValues);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        ConnectionParametersRepository repository =
                (ConnectionParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.ConnectionParameters);

        setSpinnerValueFactory(connectionExistenceSpinner);
        setSpinnerValueFactory(positiveRatioSpinner);

        ConnectionParameters connectionParameters =
                new ConnectionParameters(connectionExistenceSpinner.getValue(), positiveRatioSpinner.getValue());
        repository.registerConnectionParameters(connectionParameters);

        connectionExistenceSpinner.valueProperty().addListener(new ConnectionExistencePercentageChangeListener(repository));
        positiveRatioSpinner.valueProperty().addListener(new PositiveRatioChangeListener(repository));
    }

    private void setSpinnerValueFactory(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(MIN_SPINNER_VALUE, MAX_SPINNER_VALUE, INITIAL_SPINNER_VALUE, STEP_SPINNER_VALUE);

        spinner.setValueFactory(valueFactory);
    }

    private static class ConnectionExistencePercentageChangeListener implements ChangeListener<Integer> {
        ConnectionParametersRepository repository;

        public ConnectionExistencePercentageChangeListener(ConnectionParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updateConnectionExistencePercentage(newValue);
        }
    }

    private static class PositiveRatioChangeListener implements ChangeListener<Integer> {
        ConnectionParametersRepository repository;

        public PositiveRatioChangeListener(ConnectionParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updatePositiveConnectionsPercentage(newValue);
        }
    }
}
