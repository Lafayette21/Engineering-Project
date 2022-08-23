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

    private final ParametersValueHandler parametersValueHandler = new ParametersValueHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndPrepareParametersScreenController();

        createParametersLisView();

        parametersListView.getSelectionModel().selectedItemProperty().addListener(new ContentScreenChangeListener(contentScreensHandler));
    }

    private void createAndPrepareParametersScreenController() {
        contentScreensHandler.loadParametersScreen(Resource.ActorParameters);
        contentScreensHandler.loadParametersScreen(Resource.ConnectionParameters);
        contentScreensHandler.loadParametersScreen(Resource.SimulationParameters);

        contentScreensHandler.setContentScreen(Resource.ActorParameters);
    }

    private void createParametersLisView() {
        parametersListView.getItems()
                .addAll(Resource.ActorParameters.getResourceName(),
                        Resource.ConnectionParameters.getResourceName(),
                        Resource.SimulationParameters.getResourceName());
    }

    AnchorPane getContentScreen() {
        return contentScreen;
    }

    public ParametersValueHandler getParametersValueHandler() {
        return parametersValueHandler;
    }
}

record ContentScreenChangeListener(ContentScreensHandler contentScreensHandler) implements ChangeListener<String> {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.equals("Aktorzy")) {
            contentScreensHandler.setContentScreen(Resource.ActorParameters);
        }
        if (newValue.equals("Połączenia")) {
            contentScreensHandler.setContentScreen(Resource.ConnectionParameters);
        }
        if (newValue.equals("Symulacja")) {
            contentScreensHandler.setContentScreen(Resource.SimulationParameters);
        }
    }
}