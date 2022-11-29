package com.example.project.visualisation.util;

import com.example.project.database.model.*;
import com.example.project.exception.InstantiationNotAllowedException;
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

    private static ActorParameters actorsParametersValues;
    private static ConnectionParameters connectionsParametersValues;
    private static List<Actor> actorList;

    private RelationCreator() {
        throw new InstantiationNotAllowedException();
    }

    public static List<Relation> createRelations(ActorParameters actorValues,
                                                 ConnectionParameters connectionValues,
                                                 List<Actor> actorList) {
        setParameterValues(actorValues, connectionValues, actorList);
        List<Relation> relations = new ArrayList<>();
        actorList.stream().map(RelationCreator::createRelationsForActor).forEach(relations::addAll);

        List<Relation> filteredOutRelations = getFilteredOutRelations(relations);
        return getRelationsWithAppliedTypesTypes(filteredOutRelations);
    }

    private static void setParameterValues(ActorParameters actorParameters,
                                           ConnectionParameters connectionParameters,
                                           List<Actor> actors) {
        actorsParametersValues = actorParameters;
        connectionsParametersValues = connectionParameters;
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
        int numberOfRows = actorsParametersValues.getNumberOfRows();
        int numberOfColumns = actorsParametersValues.getNumberOfColumns();
        return new NeighbourGetter(numberOfRows, numberOfColumns);
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

    private static List<Relation> getRelationsWithAppliedTypesTypes(List<Relation> relationList) {
        return relationList.stream().peek(RelationCreator::setRelationType).collect(Collectors.toList());
    }

    private static boolean isRedundant(List<Relation> relations, int i, int j) {
        return relations.get(i).equals(relations.get(j));
    }

    private static void setRelationType(Relation relation) {
        int connectionExistencePercentage = connectionsParametersValues.getConnectionExistencePercentage();
        int positiveRatio = connectionsParametersValues.getPositiveConnectionsPercentage();

        int randomConnection = new Random()
                .nextInt(LOWER_BOUND_FOR_RANDOM_GENERATION, UPPER_BOUND_FOR_RANDOM_GENERATION);
        if (!existsConnection(connectionExistencePercentage, randomConnection)) {
            relation.setRelationType(RelationType.NONE);
        } else {
            setRelationTypeForExistingConnection(relation, positiveRatio);
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
