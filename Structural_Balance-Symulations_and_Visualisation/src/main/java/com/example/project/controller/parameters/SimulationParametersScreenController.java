package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.parametervalues.SimulationParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationParametersScreenController implements ParameterControlledScreen, Initializable {
    private ParametersScreenController screenParent;

    @FXML
    private Spinner<Integer> stepNumberSpinner;
    @FXML
    private Spinner<Double> annealingSpinner;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void updateParametersValues() {
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        SimulationParametersValues simulationParametersValues =
                new SimulationParametersValues(stepNumberSpinner.getValue(), annealingSpinner.getValue());
        valueHandler.updateValues(Resource.SimulationParameters, simulationParametersValues);

        SuccesAlertFactory.createAndShow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        SimulationParametersRepository repository = (SimulationParametersRepository) repositoryManager.getParameterRepositoryByResource(Resource.SimulationParameters);

        stepNumberSpinner.setValueFactory(createStepNumberSpinnerValueFactory());
        annealingSpinner.setValueFactory(createAnnealingSpinnerValueFactory());

        SimulationParameters parameters = new SimulationParameters(stepNumberSpinner.getValue(), annealingSpinner.getValue());
        repository.registerSimulationParameters(parameters);

        stepNumberSpinner.valueProperty().addListener(new NumberOfStepsChangeListener(repository));
        annealingSpinner.valueProperty().addListener(new AnnealingParameterChangeListener(repository));
    }

    private SpinnerValueFactory<Integer> createStepNumberSpinnerValueFactory() {
        final int minStepValue = 0;
        final int maxStepValue = 1000;
        final int initialSpinnerValue = 50;
        final int spinnerIncreaseValue = 25;

        return new SpinnerValueFactory
                .IntegerSpinnerValueFactory(minStepValue, maxStepValue, initialSpinnerValue, spinnerIncreaseValue);
    }

    private SpinnerValueFactory<Double> createAnnealingSpinnerValueFactory() {
        final double minAnnealingValue = 0.0;
        final double maxAnnealingValue = 1.0;
        final double initialAnnealingValue = 0.0;
        final double spinnerIncreaseValue = 0.1;

        return new SpinnerValueFactory
                .DoubleSpinnerValueFactory(minAnnealingValue, maxAnnealingValue, initialAnnealingValue,
                spinnerIncreaseValue);
    }

    private static class NumberOfStepsChangeListener implements ChangeListener<Integer> {
        private final SimulationParametersRepository repository;

        public NumberOfStepsChangeListener(SimulationParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
            repository.updateNumberOfSteps(newValue);
        }
    }

    private static class AnnealingParameterChangeListener implements ChangeListener<Double> {
        private final SimulationParametersRepository repository;

        public AnnealingParameterChangeListener(SimulationParametersRepository repository) {
            this.repository = repository;
        }

        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            repository.updateAnnealingParameter(newValue);
        }
    }
}
