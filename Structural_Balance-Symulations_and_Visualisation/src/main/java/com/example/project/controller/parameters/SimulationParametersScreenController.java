package com.example.project.controller.parameters;

import com.example.project.Resource;
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

    public void updateParametersValues(){
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        SimulationParametersValues simulationParametersValues =
                new SimulationParametersValues(stepNumberSpinner.getValue(), annealingSpinner.getValue());
        valueHandler.updateValues(Resource.SimulationParameters, simulationParametersValues);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stepNumberSpinner.setValueFactory(createStepNumberSpinnerValueFactory());
        annealingSpinner.setValueFactory(createAnnealingSpinnerValueFactory());
    }

    private SpinnerValueFactory<Integer> createStepNumberSpinnerValueFactory() {
        final int minStepValue = 0;
        final int maxStepValue = 1000;
        final int initialSpinnerValue = 50;
        final int spinnerIncreaseValue = 25;

        return new SpinnerValueFactory
                .IntegerSpinnerValueFactory(minStepValue, maxStepValue,
                initialSpinnerValue, spinnerIncreaseValue);
    }

    private SpinnerValueFactory<Double> createAnnealingSpinnerValueFactory() {
        final double minAnnealingValue = 0.0;
        final double maxAnnealingValue = 1.0;
        final double initialAnnealingValue = 0.0;
        final double spinnerIncreaseValue = 0.1;

        return new SpinnerValueFactory
                .DoubleSpinnerValueFactory(minAnnealingValue, maxAnnealingValue,
                initialAnnealingValue, spinnerIncreaseValue);
    }
}
