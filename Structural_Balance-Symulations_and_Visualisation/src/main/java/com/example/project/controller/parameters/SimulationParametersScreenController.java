package com.example.project.controller.parameters;

public class SimulationParametersScreenController implements ParameterControlledScreen {
    private ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
