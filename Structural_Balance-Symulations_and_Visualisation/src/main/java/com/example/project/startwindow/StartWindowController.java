package com.example.project.startwindow;

import com.example.project.visualisationgeneratorwindow.VisualisationGeneratorWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartWindowController {
    @FXML
    private Button startButton;

    public void start(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new VisualisationGeneratorWindow(stage).initializeStage();
    }


}
