package com.example.project.controller.parameters;

public class ActorsParametersScreenController implements ParameterControlledScreen {
    private ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
