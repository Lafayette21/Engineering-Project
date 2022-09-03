package com.example.project.controller;

import com.example.project.controller.parameters.ParametersValueHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationController implements ControlledScreen, Initializable {
    private MainApplicationScreenController screenParent;

    private ParametersValueHandler parametersValueHandler;

    @FXML
    private AnchorPane visualisationPanel;

    public void setParametersValueHandler(ParametersValueHandler parametersValueHandler) {
        this.parametersValueHandler = parametersValueHandler;
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
