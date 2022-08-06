package com.example.project.controller.parameters;

import com.example.project.Resource;
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
import java.util.ResourceBundle;

public class ParametersScreenController implements Initializable {
    @FXML
    private AnchorPane contentScreen;
    @FXML
    private ListView<String> parametersListView;

    private Map<Resource, Node> parameterScreens = new HashMap<>();

    public void addParameterScreen(Resource resource, Node screen) {
        parameterScreens.put(resource, screen);
    }

    public Node getParameterScreen(Resource resource) {
        return parameterScreens.get(resource);
    }

    public boolean loadParametersScreen(Resource resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource.getResourceFileName()));
            Parent loadedScreen = (Parent) myLoader.load();
            ParameterControlledScreen parameterControlledScreen = ((ParameterControlledScreen) myLoader.getController());
            parameterControlledScreen.setScreenParent(this);
            addParameterScreen(resource, loadedScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setContentScreen(Resource resource) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndPrepareParametersScreenController();

        createParametersLisView();

        parametersListView.getSelectionModel().selectedItemProperty().addListener(new ContentScreenChangeListener());
    }

    private void createAndPrepareParametersScreenController() {
        this.loadParametersScreen(Resource.ActorParameters);
        this.loadParametersScreen(Resource.ConnectionParameters);
        this.loadParametersScreen(Resource.SimulationParameters);

        this.setContentScreen(Resource.ActorParameters);
    }

    private void createParametersLisView() {
        parametersListView.getItems()
                .addAll(Resource.ActorParameters.getResourceName(),
                        Resource.ConnectionParameters.getResourceName(),
                        Resource.SimulationParameters.getResourceName());
    }

    class ContentScreenChangeListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue.equals("Aktorzy")) {
                setContentScreen(Resource.ActorParameters);
            }
            if (newValue.equals("Połączenia")) {
                setContentScreen(Resource.ConnectionParameters);
            }
            if (newValue.equals("Symulacja")) {
                setContentScreen(Resource.SimulationParameters);
            } else {
                throw new IllegalStateException("Resource not found");
            }
        }
    }

}
