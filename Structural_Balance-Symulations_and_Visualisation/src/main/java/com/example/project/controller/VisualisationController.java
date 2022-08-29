package com.example.project.controller;

public class VisualisationController implements ControlledScreen {

    private MainApplicationScreenController screenParent;

    public void cos() {

    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }
}
