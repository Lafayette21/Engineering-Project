package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationGeneratorScreenController extends AnchorPane implements ControlledScreen, Initializable {
    MainApplicationScreenController screenController;

    @FXML
    private ParametersScreenController parametersScreenController;
    @FXML
    private SummaryScreenController summaryScreenController;

    public void changeScreenToVisualisationScreen(){
        screenController.setScreen(Resource.Visualisation);
        injectParameterValueHandler();
    }

    private void injectParameterValueHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Resource.Visualisation.getResourceFileName()));
            loader.load();
            VisualisationScreenController controller = loader.getController();
            controller.setParametersValueHandler(parametersScreenController.getParametersValueHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        screenController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        summaryScreenController.injectParametersScreenController(parametersScreenController);
        summaryScreenController.injectParentScreenController(this);
    }
}
