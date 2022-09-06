package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.scene.layout.AnchorPane;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {

    }
}
