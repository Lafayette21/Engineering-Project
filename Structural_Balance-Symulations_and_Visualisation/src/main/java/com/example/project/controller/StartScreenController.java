package com.example.project.controller;

import com.example.project.Resource;
import com.example.project.database.repository.RepositoryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController implements ControlledScreen {
    private MainApplicationScreenController parentController;
    private RepositoryManager repositoryManager;

    @FXML private Button startButton;

    @Override
    public void setScreenParent(MainApplicationScreenController screenParent) {
        parentController = screenParent;
    }

    @Override
    public void setRepositoryManager(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    public void changeScreenToVisualisationGenerator(ActionEvent event) {
        parentController.setScreen(Resource.VisualisationGenerator);
    }
}
