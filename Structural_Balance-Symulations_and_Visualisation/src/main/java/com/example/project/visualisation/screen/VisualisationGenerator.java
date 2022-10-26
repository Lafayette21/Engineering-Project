package com.example.project.visualisation.screen;

import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.util.SimulationRequiredParametersHandler;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class VisualisationGenerator {
    private VisualisationGenerator() {
        throw new InstantiationNotAllowedException();
    }

    public static void generate(SimulationRequiredParametersHandler simulationRequiredParametersHandler, AnchorPane visualisationPanel) {
        List<Actor> actorList = simulationRequiredParametersHandler.getActorList();
        List<Relation> relationList = simulationRequiredParametersHandler.getRelationList();
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }
}
