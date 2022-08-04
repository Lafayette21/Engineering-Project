package com.example.project.controller;

import com.example.project.Resource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController implements ControlledScreen {

    private ScreenController controller;

    @FXML
    private Button startButton;
    @Override
    public void setScreenParent(ScreenController screenParent) {
        controller = screenParent;
    }

    public void changeScreenToVisualisationGenerator(ActionEvent event){
        controller.setScreen(Resource.VisualisationGenerator);
    }
}
