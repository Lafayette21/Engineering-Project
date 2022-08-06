package com.example.project.controller.parameters;

public class ConnectionParametersScreenController implements ParameterControlledScreen {
    ParametersScreenController screenParent;

    @Override
    public void setScreenParent(ParametersScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
