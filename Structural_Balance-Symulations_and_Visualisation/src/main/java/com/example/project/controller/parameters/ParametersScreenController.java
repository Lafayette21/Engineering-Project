package com.example.project.controller.parameters;

import com.example.project.Resource;
import com.example.project.controller.ControlledScreen;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class ParametersScreenController implements Initializable {
    @FXML
    private AnchorPane contentScreen;
    @FXML
    private ListView<String> parametersListView;

    private Map<Resource, Node> parameterScreens = new HashMap<>();

    public void addScreen(Resource resource, Node screen) {
        parameterScreens.put(resource, screen);
    }

    public Node getScreen(Resource resource) {
        return parameterScreens.get(resource);
    }

    public boolean loadScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceFileName()));
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
        if (parameterScreens.get(resource) != null) {
            if (contentScreen==null){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndPrepareParametersScreenController();

        parametersListView.getItems()
                .addAll(Resource.ActorParameters.getResourceName(),
                        Resource.ConnectionParameters.getResourceName(),
                        Resource.SimulationParameters.getResourceName());

        parametersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String selected) {
                if (selected.equals("Aktorzy")){
                    setScreen(Resource.ActorParameters);
                }
                if (selected.equals("Połączenia")){
                    setScreen(Resource.ConnectionParameters);
                }
                if (selected.equals("Symulacja")){
                    setScreen(Resource.SimulationParameters);
                }
            }
        });
    }

    private void createAndPrepareParametersScreenController() {
        this.loadScreen(Resource.ActorParameters);
        this.loadScreen(Resource.ConnectionParameters);
        this.loadScreen(Resource.SimulationParameters);

        this.setScreen(Resource.SimulationParameters);
    }
}
