package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private Button updateButton;

    public void updateParameterValues(){
        ParametersValueHandler valueHandler = screenParent.getParametersValueHandler();
        ActorsParametersValues parametersValues =
                new ActorsParametersValues((int) actorsNumberSlider.getValue());
        valueHandler.updateValues(Resource.ActorParameters, parametersValues);

        SuccesAlertFactory.createAndShow();
    }

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actorsNumberSlider.valueProperty()
                .addListener(new ActorsNumberSliderChangeListener(actorsNumberTextField));
    }
}

record ActorsNumberSliderChangeListener(TextField actorsNumberTextField)
        implements ChangeListener<Number>{
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        String value = String.valueOf(newValue.intValue());

        actorsNumberTextField.setText(value);
    }
}
