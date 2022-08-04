package com.example.project.controller.parameters;

import com.example.project.controller.VisualisationGeneratorScreenController;

public class SimulationParametersScreenController implements ParameterControlledScreen {

    private VisualisationGeneratorScreenController screenParent;

    @Override
    public void setScreenParent(VisualisationGeneratorScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
