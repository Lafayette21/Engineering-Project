package com.example.project.controller;

import com.example.project.Resource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class MainApplicationScreenController extends AnchorPane {
    private static final double HEIGHT = 800.0;
    private static final double WIDTH = 1200.0;

    private final HashMap<Resource, Node> screens = new HashMap<>();

    private static MainApplicationScreenController instance;

    private MainApplicationScreenController() {
        super();
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
    }

    public static MainApplicationScreenController getInstance() {
        if (instance == null) {
            instance = new MainApplicationScreenController();
        }
        return instance;
    }

    public void addScreen(Resource resource, Node screen) {
        screens.put(resource, screen);
    }

    public void loadScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceFileName()));
            Parent loadedScreen = myLoader.load();
            addScreen(resource, loadedScreen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setScreen(Resource resource) {
        if (screens.get(resource) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(resource));
            } else {
                getChildren().add(screens.get(resource));
            }
        } else {
            System.out.println("Screen has not been loaded!");
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
