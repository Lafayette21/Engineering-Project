package com.example.project.controller.parameters;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class ActorsParametersScreenController implements ParameterControlledScreen {
    private ParametersScreenController screenParent;

    @FXML
    private Slider actorsNumberSlider;



    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
