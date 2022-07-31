package com.example.project.controller;

public class VisualisationGeneratorScreenController implements ControlledScreen {

    ScreenController controller;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }
}
