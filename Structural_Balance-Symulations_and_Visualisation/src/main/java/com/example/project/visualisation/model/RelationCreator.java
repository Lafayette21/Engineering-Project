package com.example.project.visualisation.model;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RelationCreator {
    private final ActorsParametersValues actorsParametersValues;
    private final ConnectionsParametersValues connectionsParametersValues;
    private final List<Actor> actorList;

    public RelationCreator(ActorsParametersValues actorsParametersValues, ConnectionsParametersValues connectionsParametersValues, List<Actor> actorList) {
        this.actorsParametersValues = actorsParametersValues;
        this.connectionsParametersValues = connectionsParametersValues;
        this.actorList = actorList;
    }

    public Map<Actor, List<Relation>> createRelations() {
        return actorList.stream()
                .collect(Collectors.toMap(actor -> actor, this::createRelationsForActor));
    }

    private List<Relation> createRelationsForActor(Actor actor) {
        NeighbourGetter neighbourGetter = createNeighbourGetter();
        List<Integer> neighbours = neighbourGetter.getNeighbours(actor);
        return neighbours.stream()
                .map(neighbourId -> createRelation(actor, getActorById(neighbourId)))
                .collect(Collectors.toList());
    }

    private NeighbourGetter createNeighbourGetter() {
        int rowNumber = actorsParametersValues.rowNumber();
        int columnNumber = actorsParametersValues.columnNumber();
        return new NeighbourGetter(rowNumber, columnNumber);
    }

    private Actor getActorById(int actorId) {
        return actorList.get(actorId - 1);
    }

    private Relation createRelation(Actor firstActor, Actor secondActor) {
        int connectionCreationPercent = connectionsParametersValues.connectionCreationPercentRatio();
        int posToNegPercent = connectionsParametersValues.positiveToNegativePercentRatio();

        int randomConnection = new Random().nextInt(0, 100);
        if (!existsConnection(connectionCreationPercent, randomConnection)) {
            return new Relation(firstActor, secondActor, RelationType.NONE);
        } else {
            int randomPosToNeg = new Random().nextInt(0, 100);
            if (isPositive(posToNegPercent, randomPosToNeg)) {
                return new Relation(firstActor, secondActor, RelationType.POSITIVE);
            } else {
                return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
            }
        }
    }

    private boolean existsConnection(int connectionCreationPercent, int randomConnection) {
        return randomConnection < connectionCreationPercent;
    }

    private boolean isPositive(int posToNegPercent, int randomPosToNeg) {
        return randomPosToNeg < posToNegPercent;
    }
}
