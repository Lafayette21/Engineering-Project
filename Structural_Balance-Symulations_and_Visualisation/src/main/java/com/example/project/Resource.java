package com.example.project;

import com.example.project.controller.parameters.ParametersScreenController;

import java.util.Optional;

public enum Resource {
    StartWindow("StartScreen.fxml"),
    VisualisationGenerator("VisualisationGeneratorScreen.fxml"),
    Parameters("ParametersScreen.fxml"),
    ActorParameters("ActorsParametersScreen.fxml","Aktorzy"),
    ConnectionParameters("ConnectionParametersScreen.fxml","Połączenia"),
    SimulationParameters("SimulationParametersScreen.fxml","Symulacja");

    private String resourceFileName;
    private String resourceName;

    Resource(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    Resource(String resourceFileName, String resourceName) {
        this.resourceFileName = resourceFileName;
        this.resourceName = resourceName;
    }

    public String getResourceFileName() {
        return resourceFileName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
