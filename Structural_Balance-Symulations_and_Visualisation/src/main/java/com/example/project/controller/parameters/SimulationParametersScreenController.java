package com.example.project.controller.parameters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationParametersScreenController implements ParameterControlledScreen, Initializable {
    private ParametersScreenController screenParent;

    @FXML
    private TextField stepNumberTextField;
    @FXML
    private TextField annealingTextField;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
