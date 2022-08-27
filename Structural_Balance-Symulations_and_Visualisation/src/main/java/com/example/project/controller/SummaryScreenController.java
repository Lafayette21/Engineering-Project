package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.parametervalues.ParameterValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryScreenController implements Initializable {

    @FXML
    private Label testLabel;

    private ParametersScreenController parametersScreenController;

    public void injectParametersScreenController(ParametersScreenController parametersScreenController) {
        this.parametersScreenController = parametersScreenController;
    }

    public void updateScreenValues(){
        ParametersValueHandler parametersValueHandler = parametersScreenController.getParametersValueHandler();
        ConnectionsParametersValues parameterValueByResource =
                (ConnectionsParametersValues) parametersValueHandler.getParameterValueByResource(Resource.ConnectionParameters);

        String text = String.valueOf(parameterValueByResource.getConnectionCreationPercentRatio());
        testLabel.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Dupa");
    }
}
