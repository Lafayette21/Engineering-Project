package com.example.project.controller.parameters;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SimulationParametersScreenController implements ParameterControlledScreen {
    private ParametersScreenController screenParent;

    @FXML
    private TextField stepNumberTextField;
    @FXML
    private TextField annealingTextField;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
