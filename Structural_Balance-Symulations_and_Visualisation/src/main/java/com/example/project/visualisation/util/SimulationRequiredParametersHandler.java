package com.example.project.visualisation.util;

import com.example.project.RepositoryName;
import com.example.project.database.model.Actor;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.model.ConnectionParameters;
import com.example.project.database.model.Relation;
import com.example.project.database.repository.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class SimulationRequiredParametersHandler {
    private final RepositoryManager repositoryManager = RepositoryManager.getInstance();

    private List<Actor> actorList;
    private List<Relation> relationList;

    public SimulationRequiredParametersHandler(AnchorPane visualisationPanel) {
        setParametersValues(repositoryManager, visualisationPanel);
    }

    private void setParametersValues(RepositoryManager repositoryManager, AnchorPane visualisationPanel) {
        ActorParametersRepository actorParametersRepository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByName(RepositoryName.ACTOR_PARAMETERS);
        ActorParameters actorParameters = actorParametersRepository.getActorParameters();

        ConnectionParametersRepository connectionParametersRepository =
                (ConnectionParametersRepository) repositoryManager.getParameterRepositoryByName(RepositoryName.CONNECTION_PARAMETERS);
        ConnectionParameters connectionParameters = connectionParametersRepository.getConnectionParameters();

        CanvasPointsDistance canvasPointsDistance = getCanvasPointsDistance(actorParameters, visualisationPanel);

        actorList = ActorFactory.createActors(actorParameters, canvasPointsDistance);
        relationList = RelationCreator.createRelations(actorParameters, connectionParameters, actorList);
    }

    private CanvasPointsDistance getCanvasPointsDistance(ActorParameters actorsParameters, AnchorPane visualisationPanel) {
        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (actorsParameters.getNumberOfColumns() + 1);
        double distanceY = height / (actorsParameters.getNumberOfRows() + 1);
        return new CanvasPointsDistance(distanceX, distanceY);
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }
}
