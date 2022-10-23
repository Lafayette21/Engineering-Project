package com.example.project.visualisation.screen;

import com.example.project.RepositoryName;
import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.database.model.ActorParameters;
import com.example.project.database.model.ConnectionParameters;
import com.example.project.database.repository.ActorParametersRepository;
import com.example.project.database.repository.ConnectionParametersRepository;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
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

    public void generate(RepositoryManager repositoryManager) {
        setParametersValues(repositoryManager);
        CanvasDrawer.draw(visualisationPanel, actorList, relationList);
    }

    private void setParametersValues(RepositoryManager repositoryManager) {
        ActorParametersRepository actorParametersRepository =
                (ActorParametersRepository) repositoryManager.getParameterRepositoryByResource(RepositoryName.ACTOR_PARAMETERS);
        ActorParameters actorParameters = actorParametersRepository.getActorParameters();

        ConnectionParametersRepository connectionParametersRepository =
                (ConnectionParametersRepository) repositoryManager.getParameterRepositoryByResource(RepositoryName.CONNECTION_PARAMETERS);
        ConnectionParameters connectionParameters = connectionParametersRepository.getConnectionParameters();

        CanvasPointsDistance canvasPointsDistance = getCanvasPointsDistance(actorParameters);

        actorList = ActorFactory.createActors(actorParameters, canvasPointsDistance);
        relationList = RelationCreator.createRelations(actorParameters, connectionParameters, actorList);
    }

    private CanvasPointsDistance getCanvasPointsDistance(ActorParameters actorsParameters) {
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
