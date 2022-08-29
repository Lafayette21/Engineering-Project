package com.example.project.controller.parameters;

import com.example.project.Resource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class ContentScreensHandler {

    private final ParametersScreenController parametersScreenController;
    private final Map<Resource, Node> parameterScreens = new HashMap<>();

    public ContentScreensHandler(ParametersScreenController parametersScreenController) {
        this.parametersScreenController = parametersScreenController;
    }

    void addParameterScreen(Resource resource, Node screen) {
        parameterScreens.put(resource, screen);
    }

    boolean loadParametersScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceFileName()));
            Parent loadedScreen = myLoader.load();
            ParameterControlledScreen parameterControlledScreen = myLoader.getController();
            parameterControlledScreen.setScreenParent(parametersScreenController);
            addParameterScreen(resource, loadedScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setContentScreen(Resource resource) {
        AnchorPane contentScreen = parametersScreenController.getContentScreen();
        if (parameterScreens.get(resource) != null) {
            if (contentScreen == null) {
                contentScreen = new AnchorPane();
            }
            if (!contentScreen.getChildren().isEmpty()) {
                contentScreen.getChildren().remove(0);
                contentScreen.getChildren().add(0, parameterScreens.get(resource));
            } else {
                contentScreen.getChildren().add(parameterScreens.get(resource));
            }
            return true;
        } else {
            System.out.println("Screen has not been loaded!");
            return false;
        }
    }

    public boolean unloadScreen(Resource resource) {
        if (parameterScreens.remove(resource) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
