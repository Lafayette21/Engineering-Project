package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.VisualisationGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class VisualisationScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;
    private ParametersValueHandler parametersValueHandler;
    private VisualisationGenerator visualisationGenerator;

    @FXML
    private AnchorPane visualisationPanel;
    @FXML
    private Button generateButton;
    @FXML
    private Button startButton;

    public void generateVisualisation() {
        parametersValueHandler = getParametersValueHandler();
        visualisationGenerator = new VisualisationGenerator(visualisationPanel);
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

    public void changeToSimulationFlowScreen() {
        clearVisualisationPanel();
        sendSimulationRequiredParameters();
        screenParent.setScreen(Resource.SimulationFlow);
    }

    private void sendSimulationRequiredParameters() {
        screenParent.setUserData(getSimulationRequiredValuesDTO());
    }

    private SimulationRequiredValuesDTO getSimulationRequiredValuesDTO() {
        List<Actor> actorList = visualisationGenerator.getActorList();
        List<Relation> relationList = visualisationGenerator.getRelationList();
        SimulationParametersValues simulationParameters =
                (SimulationParametersValues) parametersValueHandler.getParameterValueByResource(Resource.SimulationParameters);
        return new SimulationRequiredValuesDTO(actorList, relationList, simulationParameters);
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
