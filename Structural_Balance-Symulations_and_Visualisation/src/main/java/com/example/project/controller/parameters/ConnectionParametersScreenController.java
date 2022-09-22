package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.parametervalues.ConnectionsParametersValues;
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
    private Spinner<Integer> connectionPercentSpinner;
    @FXML
    private Spinner<Integer> positiveToNegativeRatioSpinner;
    @FXML
    private Button updateButton;

    ParametersScreenController screenParent = new ParametersScreenController();

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    public void updateParametersValues() {
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        ConnectionsParametersValues connectionsParametersValues =
                new ConnectionsParametersValues(connectionPercentSpinner.getValue(), positiveToNegativeRatioSpinner.getValue());
        valueHandler.updateValues(Resource.ConnectionParameters, connectionsParametersValues);

        SuccesAlertFactory.createAndShow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSpinnerValueFactory(connectionPercentSpinner);
        setSpinnerValueFactory(positiveToNegativeRatioSpinner);
    }

    private void setSpinnerValueFactory(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(MIN_SPINNER_VALUE, MAX_SPINNER_VALUE, INITIAL_SPINNER_VALUE, STEP_SPINNER_VALUE);

        spinner.setValueFactory(valueFactory);
    }
}
