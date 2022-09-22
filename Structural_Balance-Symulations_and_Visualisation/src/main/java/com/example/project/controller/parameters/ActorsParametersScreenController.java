package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ActorsParametersScreenController implements ParameterControlledScreen, Initializable {
    private static final int MIN_SPINNER_VALUE = 2;
    private static final int MAX_SPINNER_VALUE = 10;
    private static final int INITIAL_SPINNER_VALUE = 2;
    private static final int STEP_SPINNER_VALUE = 1;

    private ParametersScreenController screenParent;
    @FXML
    private TextField actorsNumberTextField;
    @FXML
    private Spinner<Integer> rowSpinner;
    @FXML
    private Spinner<Integer> columnSpinner;
    @FXML
    private Button updateButton;

    public void updateParameterValues() {
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        int rowNumber = rowSpinner.getValue();
        int columnNumber = columnSpinner.getValue();

        ActorsParametersValues parametersValues = new ActorsParametersValues(rowNumber, columnNumber);
        valueHandler.updateValues(Resource.ActorParameters, parametersValues);

        updateActorsNumberTextField(rowNumber, columnNumber);
        SuccesAlertFactory.createAndShow();
    }

    private void updateActorsNumberTextField(int rowNumber, int columnNumber) {
        int numberOfActors = rowNumber * columnNumber;

        actorsNumberTextField.setText(String.valueOf(numberOfActors));
    }

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSpinnerValueFactory(rowSpinner);
        setSpinnerValueFactory(columnSpinner);
    }

    private void setSpinnerValueFactory(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(MIN_SPINNER_VALUE, MAX_SPINNER_VALUE, INITIAL_SPINNER_VALUE, STEP_SPINNER_VALUE);

        spinner.setValueFactory(valueFactory);
    }
}
