package com.example.project.visualisation.model;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;

import java.util.*;
import java.util.stream.Collectors;

public class RelationCreator {
    private static final int LOWER_BOUND_FOR_RANDOM_GENERATION = 0;
    private static final int UPPER_BOUND_FOR_RANDOM_GENERATION = 100;
    private static ActorsParametersValues actorsParametersValues;
    private static ConnectionsParametersValues connectionsParametersValues;
    private static List<Actor> actorList;

    private RelationCreator() {
        throw new RuntimeException("Class RelationCreator cannot be instantiated");
    }

    public static Set<Relation> createRelations(ActorsParametersValues actorValues,
                                         ConnectionsParametersValues connectionValues,
                                         List<Actor> actorList) {
        setParameterValues(actorValues, connectionValues, actorList);
        List<Relation> relations = new ArrayList<>();
        actorList.stream().map(RelationCreator::createRelationsForActor).forEach(relations::addAll);
        return getFilteredOutRelations(relations);
    }

    private static void setParameterValues(ActorsParametersValues actorValues, ConnectionsParametersValues connectionValues, List<Actor> actors) {
        actorsParametersValues = actorValues;
        connectionsParametersValues = connectionValues;
        actorList = actors;
    }

    private static Set<Relation> getFilteredOutRelations(List<Relation> relations) {
        HashSet<Relation> relationSet = new HashSet<>();
        for (int i = 0; i < relations.size(); i++) {
            for (int j = i + 1; j < relations.size(); j++) {
                if (isRedundant(relations, i, j)) {
                    relationSet.add(relations.get(i));
                }
            }
        }
        return relationSet;
    }

    private static boolean isRedundant(List<Relation> relations, int i, int j) {
        return relations.get(i).equals(relations.get(j));
    }


    private static List<Relation> createRelationsForActor(Actor actor) {
        NeighbourGetter neighbourGetter = createNeighbourGetter();
        List<Integer> neighbours = neighbourGetter.getNeighbours(actor);
        return neighbours.stream()
                .map(neighbourId -> createRelation(actor, getActorById(neighbourId)))
                .collect(Collectors.toList());
    }

    private static NeighbourGetter createNeighbourGetter() {
        int rowNumber = actorsParametersValues.rowNumber();
        int columnNumber = actorsParametersValues.columnNumber();
        return new NeighbourGetter(rowNumber, columnNumber);
    }

    private static Actor getActorById(int actorId) {
        return actorList.get(actorId - 1);
    }

    private static Relation createRelation(Actor firstActor, Actor secondActor) {
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

    private static boolean existsConnection(int connectionCreationPercent, int randomConnection) {
        return randomConnection < connectionCreationPercent;
    }

    private static Relation createRelationWithType(Actor firstActor, Actor secondActor, int posToNegPercent) {
        int randomPosToNeg =
                new Random().nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (isPositive(posToNegPercent, randomPosToNeg)) {
            return new Relation(firstActor, secondActor, RelationType.POSITIVE);
        } else {
            return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
        }
    }

    private static boolean isPositive(int posToNegPercent, int randomPosToNeg) {
        return randomPosToNeg < posToNegPercent;
    }

}
