package com.example.project.controller;

public class SimulationFlowScreenController implements ControlledScreen {
    private MainApplicationScreenController screenParent;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
