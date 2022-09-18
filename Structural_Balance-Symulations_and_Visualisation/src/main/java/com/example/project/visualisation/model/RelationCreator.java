package com.example.project.visualisation.model;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;

import java.util.*;
import java.util.stream.Collectors;

public class RelationCreator {
    private static final int LOWER_BOUND_FOR_RANDOM_GENERATION = 0;
    private static final int UPPER_BOUND_FOR_RANDOM_GENERATION = 100;
    private final ActorsParametersValues actorsParametersValues;
    private final ConnectionsParametersValues connectionsParametersValues;
    private final List<Actor> actorList;

    public RelationCreator(ActorsParametersValues actorsParametersValues, ConnectionsParametersValues connectionsParametersValues, List<Actor> actorList) {
        this.actorsParametersValues = actorsParametersValues;
        this.connectionsParametersValues = connectionsParametersValues;
        this.actorList = actorList;
    }

    public Set<Relation> createRelations() {
        List<Relation> relations = new ArrayList<>();
        actorList.stream().map(this::createRelationsForActor).forEach(relations::addAll);
        return new HashSet<>(relations);
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

        int randomConnection =
                new Random().nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (!existsConnection(connectionCreationPercent, randomConnection)) {
            return new Relation(firstActor, secondActor, RelationType.NONE);
        } else {
            return createRelationWithType(firstActor, secondActor, posToNegPercent);
        }
    }

    private boolean existsConnection(int connectionCreationPercent, int randomConnection) {
        return randomConnection < connectionCreationPercent;
    }

    private Relation createRelationWithType(Actor firstActor, Actor secondActor, int posToNegPercent) {
        int randomPosToNeg =
                new Random().nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (isPositive(posToNegPercent, randomPosToNeg)) {
            return new Relation(firstActor, secondActor, RelationType.POSITIVE);
        } else {
            return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
        }
    }

    private boolean isPositive(int posToNegPercent, int randomPosToNeg) {
        return randomPosToNeg < posToNegPercent;
    }

}
