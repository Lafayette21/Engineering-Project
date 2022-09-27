package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.visualisation.model.*;
import com.example.project.visualisation.util.ActorFactory;
import com.example.project.visualisation.util.CanvasPointsDistance;
import com.example.project.visualisation.util.RelationCreator;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    private int rowNumber;
    private int columnNumber;
    private int connectionCreationPercentRatio;
    private int positiveToNegativePercentRatio;
    private CanvasPointsDistance canvasPointsDistance;
    private List<Actor> actorList;
    private Set<Relation> relationSet;


    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {
        setParametersValues(parametersValueHandler);
        CanvasDrawer.draw(visualisationPanel,actorList, relationSet);
    }

    private void setParametersValues(ParametersValueHandler parametersValueHandler) {
        ActorsParametersValues actorParameters = (ActorsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);
        ConnectionsParametersValues connectionParameters = (ConnectionsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ConnectionParameters);

        rowNumber = actorParameters.rowNumber();
        columnNumber = actorParameters.columnNumber();
        connectionCreationPercentRatio = connectionParameters.connectionCreationPercentRatio();
        positiveToNegativePercentRatio = connectionParameters.positiveToNegativePercentRatio();
        canvasPointsDistance = getCanvasPointsDistance();
        actorList = ActorFactory.createActors(actorParameters, canvasPointsDistance);
        relationSet = RelationCreator.createRelations(actorParameters, connectionParameters, actorList);
    }

    private CanvasPointsDistance getCanvasPointsDistance() {
        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (columnNumber + 1);
        double distanceY = height / (rowNumber + 1);
        return new CanvasPointsDistance(distanceX, distanceY);
    }

    private Actor getActorById(int actorId) {
        for (Actor actor : actorList) {
            if (actor.getActorId() == actorId) {
                return actor;
            }
        }
        throw new RuntimeException(String.format("Actor with id {} not found", actorId));
    }
}
