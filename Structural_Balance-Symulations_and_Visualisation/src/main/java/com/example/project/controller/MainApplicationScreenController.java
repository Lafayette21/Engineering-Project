package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.database.repository.RepositoryManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class MainApplicationScreenController extends AnchorPane {
    private final HashMap<Resource, Node> screens = new HashMap<>();
    private final RepositoryManager repositoryManager = RepositoryManager.getInstance();

    public MainApplicationScreenController() {
        super();
        this.setHeight(450.0);
        this.setWidth(650.0);
    }

    public void addScreen(Resource resource, Node screen) {
        screens.put(resource, screen);
    }

    public boolean loadScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceFileName()));
            Parent loadedScreen = myLoader.load();
            ControlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);
            myScreenController.setRepositoryManager(repositoryManager);
            addScreen(resource, loadedScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(Resource resource) {
        if (screens.get(resource) != null) {
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

    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
}
