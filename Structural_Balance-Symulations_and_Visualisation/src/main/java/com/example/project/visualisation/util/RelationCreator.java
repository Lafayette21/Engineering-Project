package com.example.project.visualisation.util;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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

    public static List<Relation> createRelations(ActorsParametersValues actorValues,
                                                 ConnectionsParametersValues connectionValues,
                                                 List<Actor> actorList) {
        setParameterValues(actorValues, connectionValues, actorList);
        List<Relation> relations = new ArrayList<>();
        actorList.stream().map(RelationCreator::createRelationsForActor).forEach(relations::addAll);

        List<Relation> filteredOutRelations = getFilteredOutRelations(relations);
        List<Relation> relationsWithTypes = getRelationsWithTypes(filteredOutRelations);
        return relationsWithTypes;
    }

    private static void setParameterValues(ActorsParametersValues actorValues, ConnectionsParametersValues connectionValues, List<Actor> actors) {
        actorsParametersValues = actorValues;
        connectionsParametersValues = connectionValues;
        actorList = actors;
    }

    private static List<Relation> createRelationsForActor(Actor actor) {
        NeighbourGetter neighbourGetter = createNeighbourGetter();
        List<Integer> neighbours = neighbourGetter.getNeighbours(actor);
        return neighbours.stream()
                .map(neighbourId -> new Relation(actor, getActorById(neighbourId)))
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

    private static List<Relation> getFilteredOutRelations(List<Relation> relations) {
        HashSet<Relation> relationSet = new HashSet<>();
        for (int i = 0; i < relations.size(); i++) {
            for (int j = i + 1; j < relations.size(); j++) {
                if (isRedundant(relations, i, j)) {
                    relationSet.add(relations.get(i));
                }
            }
        }
        return new ArrayList<>(relationSet);
    }

    private static List<Relation> getRelationsWithTypes(List<Relation> relationList) {
        return relationList.stream().peek(RelationCreator::setRelationType).collect(Collectors.toList());
    }

    private static boolean isRedundant(List<Relation> relations, int i, int j) {
        return relations.get(i).equals(relations.get(j));
    }

    private static void setRelationType(Relation relation) {
        int connectionCreationPercent = connectionsParametersValues.connectionCreationPercentRatio();
        int posToNegPercent = connectionsParametersValues.positiveToNegativePercentRatio();

        int randomConnection = new Random()
                .nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (!existsConnection(connectionCreationPercent, randomConnection)) {
            relation.setRelationType(RelationType.NONE);
        } else {
            setRelationTypeForExistingConnection(relation, posToNegPercent);
        }

    }

    private static boolean existsConnection(int connectionCreationPercent, int randomConnection) {
        return randomConnection < connectionCreationPercent;
    }

    private static void setRelationTypeForExistingConnection(Relation relation, int posToNegPercent) {
        int randomPosToNeg = new Random()
                .nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (isPositive(posToNegPercent, randomPosToNeg)) {
            relation.setRelationType(RelationType.POSITIVE);
        } else {
            relation.setRelationType(RelationType.NEGATIVE);
        }

    }

    private static boolean isPositive(int posToNegPercent, int randomPosToNeg) {
        return randomPosToNeg < posToNegPercent;
    }

}
