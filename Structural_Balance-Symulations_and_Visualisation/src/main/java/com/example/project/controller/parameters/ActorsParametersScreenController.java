package com.example.project.controller.parameters;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.SimulationParametersValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class ActorsParametersScreenController implements ParameterControlledScreen, Initializable {
    private ParametersScreenController screenParent;

    @FXML
    private Slider actorsNumberSlider;


    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
