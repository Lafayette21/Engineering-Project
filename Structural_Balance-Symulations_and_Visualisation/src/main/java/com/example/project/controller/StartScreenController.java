package com.example.project.controller;

public class StartScreenController implements ControlledScreen {

    private ScreenController controller;
    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }
}
