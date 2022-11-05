package com.example.project.controller.simulationflow;

import com.example.project.RepositoryName;
import com.example.project.database.model.NewSimulationParameters;
import com.example.project.database.repository.NewSimulationParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ParameterTabController implements Initializable, TabController, StateControllable {
    @FXML
    private StatePanelController statePanelController;

    @FXML
    private Spinner<Integer> stepNumberSpinner;
    @FXML
    private Spinner<Double> temperatureSpinner;
    @FXML
    private Spinner<Integer> timeSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        NewSimulationParametersRepository repository = (NewSimulationParametersRepository) repositoryManager
                        .getParameterRepositoryByName(RepositoryName.NEW_SIMULATION_PARAMETERS);

        setStepNumberSpinnerValueFactory();
        setTimeSpinnerValueFactory();
        setTemperatureSpinnerValueFactory();

        NewSimulationParameters initialSimulationParameters =
                new NewSimulationParameters(stepNumberSpinner.getValue(), temperatureSpinner.getValue(), timeSpinner.getValue());
        repository.registerSimulationParameters(initialSimulationParameters);

        stepNumberSpinner.valueProperty().addListener(new StepNumberSpinnerChangeListener(repository));
        temperatureSpinner.valueProperty().addListener(new TemperatureSpinnerChangeListener(repository));
        timeSpinner.valueProperty().addListener(new TimeSpinnerChangeListener(repository));
    }

    private void setStepNumberSpinnerValueFactory() {
        int minSpinnerValue = 50;
        int maxSpinnerValue = 300;
        int initialSpinnerValue = 50;
        int stepSpinnerValue = 25;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(minSpinnerValue, maxSpinnerValue, initialSpinnerValue, stepSpinnerValue);
        stepNumberSpinner.setValueFactory(valueFactory);
    }

    private void setTimeSpinnerValueFactory() {
        int minSpinnerValue = 0;
        int maxSpinnerValue = 60;
        int initialSpinnerValue = 0;
        int stepSpinnerValue = 1;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(minSpinnerValue, maxSpinnerValue, initialSpinnerValue, stepSpinnerValue);
        timeSpinner.setValueFactory(valueFactory);
    }

    private void setTemperatureSpinnerValueFactory() {
        double minSpinnerValue = 0;
        double maxSpinnerValue = 100;
        double initialSpinnerValue = 0;
        double stepSpinnerValue = 0.3;
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory
                .DoubleSpinnerValueFactory(minSpinnerValue, maxSpinnerValue, initialSpinnerValue, stepSpinnerValue);
        temperatureSpinner.setValueFactory(valueFactory);
    }

    @Override
    public void nextSimulationStep() {

    }

    @Override
    public void previousSimulationStep() {

    }

    @Override
    public void skipToEnd() {

    }

    @Override
    public StatePanelController getStatePanelController() {
        return statePanelController;
    }

    private record StepNumberSpinnerChangeListener(NewSimulationParametersRepository repository)
            implements ChangeListener<Integer> {
        @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                repository.updateNumberOfSteps(newValue);
            }
        }

    private record TemperatureSpinnerChangeListener(NewSimulationParametersRepository repository)
            implements ChangeListener<Double> {
        @Override
            public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
                repository.updateTemperature(newValue);
            }
        }

    private record TimeSpinnerChangeListener(NewSimulationParametersRepository repository)
            implements ChangeListener<Integer> {
        @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                repository.updateTime(newValue);
            }
        }
}
