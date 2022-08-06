package com.example.project.controller.parameters;

import com.example.project.controller.VisualisationGeneratorScreenController;

public class ConnectionParametersScreenController implements ParameterControlledScreen{

    ParametersScreenController screenParent;
    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
