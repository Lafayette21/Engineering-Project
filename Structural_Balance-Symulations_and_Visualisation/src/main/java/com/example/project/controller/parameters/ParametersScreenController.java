package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.controller.VisualisationGeneratorScreenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametersScreenController implements Initializable {
    @FXML
    private ActorsParametersScreenController actorsParametersScreenController;

    @FXML
    private AnchorPane contentScreen;

    @FXML
    private ListView<String> parametersListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parametersListView.getItems()
                .addAll(Resource.ActorParameters.getResourceName(),
                        Resource.ConnectionParameters.getResourceName(),
                        Resource.SimulationParameters.getResourceName());
    }
}
