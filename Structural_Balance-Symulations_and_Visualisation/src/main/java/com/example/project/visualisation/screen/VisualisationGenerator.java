package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.visualisation.util.ActorFactory;
import com.example.project.visualisation.util.CanvasPointsDistance;
import com.example.project.visualisation.util.RelationCreator;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class VisualisationGenerator {
    private final AnchorPane visualisationPanel;

    private List<Actor> actorList;
    private List<Relation> relationList;

    public VisualisationGenerator(AnchorPane visualisationPanel) {
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {
        setParametersValues(parametersValueHandler);
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }

    private void setParametersValues(ParametersValueHandler parametersValueHandler) {
        ActorsParametersValues actorParameters = (ActorsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);
        ConnectionsParametersValues connectionParameters = (ConnectionsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ConnectionParameters);

        CanvasPointsDistance canvasPointsDistance = getCanvasPointsDistance(actorParameters);

        actorList = ActorFactory.createActors(actorParameters, canvasPointsDistance);
        relationList = RelationCreator.createRelations(actorParameters, connectionParameters, actorList);
    }

    private CanvasPointsDistance getCanvasPointsDistance(ActorsParametersValues actorsParameter) {
        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (actorsParameter.columnNumber() + 1);
        double distanceY = height / (actorsParameter.rowNumber() + 1);
        return new CanvasPointsDistance(distanceX, distanceY);
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }
}
