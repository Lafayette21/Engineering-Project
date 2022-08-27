package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ConnectionsParametersValues;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        ParametersValueHandler valueHandler = parametersScreenController.getParametersValueHandler();
        try{
            ConnectionsParametersValues connectionsParametersValues =
                    (ConnectionsParametersValues) valueHandler.getParameterValueByResource(Resource.ConnectionParameters);
            String text = String.valueOf(connectionsParametersValues.positiveToNegativePercentRatio());
            testLabel.setText(text);
        } catch (NullPointerException e){
            showAlert();
        }

    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Część z parametrów nie była jeszcze ustawiona. Ustaw je aby móc zobaczyć podsumowanie");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Dupa");
    }
}
