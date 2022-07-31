package com.example.project.controller;

import com.example.project.Resource;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class ScreenController extends AnchorPane {
    private HashMap<Resource, Node> screens = new HashMap<>();

    public ScreenController() {
        super();
    }

    public void addScreen(Resource resource, Node screen) {
        screens.put(resource, screen);
    }

    public Node getScreen(Resource resource) {
        return screens.get(resource);
    }

    public boolean loadScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceName()));
            Parent loadedScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(resource, loadedScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(Resource resource) {
        if (screens.get(resource) != null) {
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(resource));
            } else {
                getChildren().add(screens.get(resource));
            }
            return true;
        } else {
            System.out.println("Screen has not been loaded!");
            return false;
        }
    }

    public boolean unloadScreen(Resource resource) {
        if (screens.remove(resource) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }


}
