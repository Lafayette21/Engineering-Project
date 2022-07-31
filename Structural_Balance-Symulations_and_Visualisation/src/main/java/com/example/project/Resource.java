package com.example.project;

public enum Resource {
    StartWindow("StartScreen.fxml"),VisualisationGenerator("VisualisationGeneratorScreen.fxml");

    private String resourceName;

    Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
