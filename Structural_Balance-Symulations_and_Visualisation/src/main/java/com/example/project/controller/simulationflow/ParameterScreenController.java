package com.example.project.controller.simulationflow;

import com.example.project.RepositoryName;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ParameterScreenController implements Initializable {
    @FXML
    private Spinner<Double> temperatureSpinner;
    @FXML
    private Spinner<Integer> timeSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        SimulationParametersRepository repository = (SimulationParametersRepository) repositoryManager
                .getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);

        setTimeSpinnerValueFactory();
        setTemperatureSpinnerValueFactory();

        SimulationParameters initialSimulationParameters =
                new SimulationParameters(temperatureSpinner.getValue(), timeSpinner.getValue());
        repository.registerSimulationParameters(initialSimulationParameters);

        temperatureSpinner.valueProperty().addListener(new TemperatureSpinnerChangeListener(repository));
        timeSpinner.valueProperty().addListener(new TimeSpinnerChangeListener(repository));
    }

    private void setTimeSpinnerValueFactory() {
        int minSpinnerValue = 1;
        int maxSpinnerValue = 10;
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

    private record TemperatureSpinnerChangeListener(SimulationParametersRepository repository)
            implements ChangeListener<Double> {
        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            repository.updateTemperature(newValue);
        }
    }

    private record TimeSpinnerChangeListener(SimulationParametersRepository repository)
            implements ChangeListener<Integer> {
        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updateTime(newValue);
        }
    }
}
