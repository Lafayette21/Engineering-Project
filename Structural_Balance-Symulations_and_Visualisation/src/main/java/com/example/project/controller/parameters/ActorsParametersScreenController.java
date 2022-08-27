package com.example.project.controller.parameters;

import com.example.project.parametervalues.ActorsParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ActorsParametersScreenController implements ParameterControlledScreen, Initializable {
    private ParametersScreenController screenParent;

    @FXML
    private Slider actorsNumberSlider;
    @FXML
    private TextField actorsNumberTextField;


    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActorsParametersValues actorsParametersValues = new ActorsParametersValues();

        screenParent.getParametersValueHandler();

        actorsNumberSlider.valueProperty()
                .addListener(new ActorsNumberSliderChangeListener(actorsParametersValues,actorsNumberTextField));
    }
}

record ActorsNumberSliderChangeListener(ActorsParametersValues actorsParametersValues,TextField actorsNumberTextField)
        implements ChangeListener<Number>{
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        int actorsNumber = newValue.intValue();

        actorsParametersValues.setActorsNumber(actorsNumber);
        actorsNumberTextField.setText(String.valueOf(actorsNumber));
    }
}
