package com.example.project.controller.parameters;

import com.example.project.Resource;
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
    private AnchorPane contentScreen;
    @FXML
    private ListView<String> parametersListView;

    private final ContentScreensHandler contentScreensHandler = new ContentScreensHandler(this);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndPrepareParametersScreenController();

        createParametersLisView();

        parametersListView.getSelectionModel().selectedItemProperty()
                .addListener(new ContentScreenChangeListener());
    }

    private void createAndPrepareParametersScreenController() {
        contentScreensHandler.loadParametersScreen(Resource.ActorParameters);
        contentScreensHandler.loadParametersScreen(Resource.ConnectionParameters);

        contentScreensHandler.setContentScreen(Resource.ActorParameters);
    }

    private void createParametersLisView() {
        parametersListView.getItems()
                .addAll(Resource.ActorParameters.getResourceName(),
                        Resource.ConnectionParameters.getResourceName());
    }

    AnchorPane getContentScreen() {
        return contentScreen;
    }

    private class ContentScreenChangeListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String actorsResourceName = Resource.ActorParameters.getResourceName();
            String connectionResourceName = Resource.ConnectionParameters.getResourceName();
            String simulationResourceName = Resource.SimulationParameters.getResourceName();

            if (newValue.equals(actorsResourceName)) {
                contentScreensHandler.setContentScreen(Resource.ActorParameters);
            }
            if (newValue.equals(connectionResourceName)) {
                contentScreensHandler.setContentScreen(Resource.ConnectionParameters);
            }
            if (newValue.equals(simulationResourceName)) {
                contentScreensHandler.setContentScreen(Resource.SimulationParameters);
            }
        }
    }
}

