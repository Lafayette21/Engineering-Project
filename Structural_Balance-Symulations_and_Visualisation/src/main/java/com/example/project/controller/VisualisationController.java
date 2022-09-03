package com.example.project.controller;

import com.example.project.visualisation.ConnectionMatrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationController implements ControlledScreen, Initializable {
    private MainApplicationScreenController screenParent;

    @FXML
    private AnchorPane visualisationPanel;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        this.screenParent = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(5);
        connectionMatrix.createConnections(70);
    }
}
