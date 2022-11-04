package com.example.project.controller.simulationflow;

import com.example.project.database.repository.RepositoryManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ParameterTabController implements Initializable {
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

        setStepNumberSpinnerValueFactory();
        setTimeSpinnerValueFactory();
        setTemperatureSpinnerValueFactory();
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
}
