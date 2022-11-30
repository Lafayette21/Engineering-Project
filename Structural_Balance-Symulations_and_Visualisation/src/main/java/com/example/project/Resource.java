package com.example.project;

public enum Resource {
    StartWindow("StartScreen.fxml"),
    VisualisationGenerator("VisualisationGeneratorScreen.fxml"),
    Parameters("ParametersScreen.fxml"),
    Visualisation("VisualisationScreen.fxml"),
    SimulationFlow("SimulationFlowScreen.fxml"),
    ActorParameters("ActorsParametersScreen.fxml"),
    ConnectionParameters("ConnectionParametersScreen.fxml"),
    SimulationParameters("SimulationParametersScreen.fxml");

    private final String resourceFileName;

    Resource(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public String getResourceFileName() {
        return resourceFileName;
    }
}
