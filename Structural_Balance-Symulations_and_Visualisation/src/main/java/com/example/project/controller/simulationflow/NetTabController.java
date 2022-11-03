package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.screen.CanvasDrawer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class NetTabController {
    @FXML
    private StatePanelController statePanelController;

    @FXML
    private AnchorPane visualisationPanel;

    public void prepareInitialVisualisation(List<Actor> actorList, List<Relation> relationList, SimulationParameters parameters) {
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }
}
