package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.parametervalues.ParameterValue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionParametersScreenController implements ParameterControlledScreen, Initializable {
    private static final int MIN_SPINNER_VALUE = 0;
    private static final int MAX_SPINNER_VALUE = 100;
    private static final int INITIAL_SPINNER_VALUE = 0;
    private static final int STEP_SPINNER_VALUE = 5;

    @FXML
    private Spinner<Integer> connectionPercentSpinner;
    @FXML
    private Spinner<Integer> positiveToNegativeRatioSpinner;

    ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSpinnerValueFactory(connectionPercentSpinner);
        setSpinnerValueFactory(positiveToNegativeRatioSpinner);

        ConnectionsParametersValues parametersValues = new ConnectionsParametersValues();

        connectionPercentSpinner.valueProperty()
                .addListener(new ConnectionPercentChangeListener(parametersValues));
        positiveToNegativeRatioSpinner.valueProperty()
                .addListener(new PositiveToNegativeRatioChangeListener(parametersValues));
    }

    private void setSpinnerValueFactory(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(MIN_SPINNER_VALUE, MAX_SPINNER_VALUE, INITIAL_SPINNER_VALUE, STEP_SPINNER_VALUE);

        spinner.setValueFactory(valueFactory);
    }
}

record ConnectionPercentChangeListener(ConnectionsParametersValues parametersValues)
        implements ChangeListener<Integer> {
    @Override
    public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
        parametersValues.setConnectionCreationPercentRatio(newValue);
    }
}

record PositiveToNegativeRatioChangeListener(ConnectionsParametersValues parametersValues)
        implements ChangeListener<Integer> {
    @Override
    public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
        parametersValues.setPositiveToNegativePercentRatio(newValue);
    }
}

