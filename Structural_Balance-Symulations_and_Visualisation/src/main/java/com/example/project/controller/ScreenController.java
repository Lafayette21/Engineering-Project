package com.example.project.controller;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class ScreenController extends AnchorPane {
    private HashMap<String, Node> screens = new HashMap<>();

    public ScreenController() {
        super();
    }

    public void addScreen(String name,Node screen){
        screens.put(name,screen);
    }

    public Node getScreen(String name){
        return screens.get(name);
    }

    public boolean loadScreen(String screenName,String resourceName){
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resourceName));
            Parent loadedScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(screenName,loadedScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(final String screenName){
        if (screens.get(screenName)!= null){
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()){
                getChildren().remove(0);
                getChildren().add(0,screens.get(screenName));
            } else {
                getChildren().add(screens.get(screenName));
            }
            return true;
        } else {
            System.out.println("Screen has not been loaded!");
            return false;
        }
    }

    public boolean unloadScreen(String screenName){
        if (screens.remove(screenName) == null){
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }


}
