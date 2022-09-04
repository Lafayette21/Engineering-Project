package com.example.project.controller;

import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.visualisation.actorshandler.ActorHandler;
import com.example.project.visualisation.screen.VisualisationGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationScreenController implements ControlledScreen, Initializable {
    private MainApplicationScreenController screenParent;
    private ParametersValueHandler parametersValueHandler;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;

    public void generateVisualisation(ActionEvent event) {
        parametersValueHandler = getParametersValueHandler();
        VisualisationGenerator generator = new VisualisationGenerator(parametersValueHandler, visualisationPanel);
        generator.generate(parametersValueHandler);
    }

    private ParametersValueHandler getParametersValueHandler() {
        return (ParametersValueHandler) screenParent.getUserData();
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
