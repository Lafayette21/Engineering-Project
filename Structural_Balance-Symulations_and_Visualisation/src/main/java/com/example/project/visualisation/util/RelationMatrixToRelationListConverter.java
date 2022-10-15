package com.example.project.visualisation.util;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.simulation.RelationMatrix;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;
import com.example.project.database.model.RelationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelationMatrixToRelationListConverter {
    private static int[][] matrix;
    private static List<Relation> relationList;

    private RelationMatrixToRelationListConverter() {
        throw new InstantiationNotAllowedException();
    }

    public static List<Relation> convert(RelationMatrix relationMatrix, List<Relation> comparativeRelationList) {
        setStaticParameters(relationMatrix, comparativeRelationList);
        int numberOfActors = relationMatrix.getNumberOfActors();

        List<Relation> relations = new ArrayList<>();
        for (int i = 0; i < numberOfActors; i++) {
            for (int j = 0; j < i; j++) {
                if (existsRelation(i + 1, j + 1)) {
                    relations.add(getRelation(i + 1, j + 1));
                }
            }
        }
        return relations;
    }

    private static void setStaticParameters(RelationMatrix relationMatrix, List<Relation> comparativeRelationList) {
        matrix = relationMatrix.getMatrix();
        relationList = comparativeRelationList;
    }

    private static boolean existsRelation(int firstActorId, int secondActorId) {
        Relation relation = new Relation(new Actor(firstActorId), new Actor(secondActorId));
        return relationList.contains(relation);
    }

    private static Relation getRelation(int firstActorId, int secondActorId) {
        Actor firstActor = getActorFromRelation(firstActorId);
        Actor secondActor = getActorFromRelation(secondActorId);
        if (matrix[firstActorId - 1][secondActorId - 1] == 0) {
            return new Relation(firstActor, secondActor, RelationType.NONE);
        }
        if (matrix[firstActorId - 1][secondActorId - 1] == -1) {
            return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
        }
        return new Relation(firstActor, secondActor, RelationType.POSITIVE);
    }

    private static Actor getActorFromRelation(int actorId) {
        Optional<Actor> actor = relationList.stream()
                .filter(relation -> existsActorInRelation(relation, actorId))
                .map(relation -> getActorById(relation, actorId)).findFirst();
        return actor.get();
    }

    private static Actor getActorById(Relation relation, int seekedActorId) {
        if (relation.getFirstActor().getActorId() == seekedActorId) {
            return relation.getFirstActor();
        }
        return relation.getSecondActor();
    }

    private static boolean existsActorInRelation(Relation relation, int seekedActorId) {
        return relation.getFirstActor().getActorId() == seekedActorId ||
                relation.getSecondActor().getActorId() == seekedActorId;
    }
}
