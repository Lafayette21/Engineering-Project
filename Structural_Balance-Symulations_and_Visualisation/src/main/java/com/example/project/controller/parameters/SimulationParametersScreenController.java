package com.example.project.controller.parameters;

import com.example.project.controller.VisualisationGeneratorScreenController;

public class SimulationParametersScreenController implements ParameterControlledScreen {

    private ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
