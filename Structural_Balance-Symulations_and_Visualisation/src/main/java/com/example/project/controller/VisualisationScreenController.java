package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.visualisation.screen.VisualisationGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class VisualisationScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;
    private ParametersValueHandler parametersValueHandler;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;
    @FXML
    private Button startButton;

    public void generateVisualisation() {
        parametersValueHandler = getParametersValueHandler();
        VisualisationGenerator visualisationGenerator = new VisualisationGenerator(visualisationPanel);
        visualisationGenerator.generate(parametersValueHandler);
    }

    private ParametersValueHandler getParametersValueHandler() {
        return (ParametersValueHandler) screenParent.getUserData();
    }

    public void changeScreenToVisualisationGeneratorScreen() {
        clearVisualisationPanel();
        screenParent.setScreen(Resource.VisualisationGenerator);
    }

    private void clearVisualisationPanel() {
        visualisationPanel.getChildren().clear();
    }

    public void changeToSimulationFlowScreen(){
        clearVisualisationPanel();
        screenParent.setScreen(Resource.SimulationFlow);
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
