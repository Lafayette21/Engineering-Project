package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.visualisation.screen.VisualisationGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;
    private ParametersValueHandler parametersValueHandler;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;

    public void generateVisualisation() {
        parametersValueHandler = getParametersValueHandler();
        VisualisationGenerator generator = new VisualisationGenerator(parametersValueHandler, visualisationPanel);
        generator.generate(parametersValueHandler);
    }

    private ParametersValueHandler getParametersValueHandler() {
        return (ParametersValueHandler) screenParent.getUserData();
    }

    public void changeScreenToVisualisationGeneratorScreen(){
        clearVisualisationPanel();
        screenParent.setScreen(Resource.VisualisationGenerator);
    }

    private void clearVisualisationPanel() {
        visualisationPanel.getChildren().clear();
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
