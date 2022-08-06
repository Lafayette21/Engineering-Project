package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParameterControlledScreen;
import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen {

    MainApplicationScreenController controller;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private VisualisationScreenController visualisationScreenController;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }

}
