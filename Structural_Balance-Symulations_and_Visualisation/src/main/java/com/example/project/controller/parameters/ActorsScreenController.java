package com.example.project.controller.parameters;

import com.example.project.controller.ControlledScreen;
import com.example.project.controller.MainApplicationScreenController;

public class ActorsScreenController implements ControlledScreen {

    private MainApplicationScreenController controller;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }
}
