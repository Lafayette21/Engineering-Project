package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParameterControlledScreen;
import com.example.project.controller.parameters.ParametersScreenController;
import javafx.beans.property.DoubleProperty;
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

    Map<Resource, Node> parametersScreens = new HashMap<>();

    public void addScreen(Resource resource, Node screen) {
        parametersScreens.put(resource, screen);
    }

    public Node getScreen(Resource resource) {
        return parametersScreens.get(resource);
    }

    public boolean loadScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceName()));
            Parent loadedScreen = (Parent) myLoader.load();
            ParameterControlledScreen myScreenController = ((ParameterControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(resource, loadedScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(Resource resource) {
        if (parametersScreens.get(resource) != null) {
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, parametersScreens.get(resource));
            } else {
                getChildren().add(parametersScreens.get(resource));
            }
            return true;
        } else {
            System.out.println("Screen has not been loaded!");
            return false;
        }
    }

    public boolean unloadScreen(Resource resource) {
        if (parametersScreens.remove(resource) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        controller = screenParent;
    }
}
