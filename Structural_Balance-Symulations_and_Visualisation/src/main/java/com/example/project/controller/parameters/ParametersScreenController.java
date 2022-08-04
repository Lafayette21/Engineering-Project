package com.example.project.controller.parameters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametersScreenController implements Initializable {
    @FXML private ActorsParametersScreenController actorsParametersScreenController;
    @FXML private ListView<String> parametersListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parametersListView.getItems().addAll("Aktorzy","Połączenia","Symulacja");
    }
}
