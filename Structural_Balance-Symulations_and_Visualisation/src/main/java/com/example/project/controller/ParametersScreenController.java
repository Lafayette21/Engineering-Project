package com.example.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametersScreenController implements Initializable {
    @FXML
    private ListView<String> parametersListView;

    String[] parameters = {"Aktorzy","Połączenia","Symulacja"};

    String selectedParameter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parametersListView.getItems().addAll(parameters);
    }

    public void cos(){

    }
}
