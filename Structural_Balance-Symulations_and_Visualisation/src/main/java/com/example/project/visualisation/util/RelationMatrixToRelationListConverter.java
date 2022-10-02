package com.example.project.visualisation.util;

import com.example.project.simulation.RelationMatrix;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.ArrayList;
import java.util.List;

public class RelationMatrixToRelationListConverter {
    private static int[][] matrix;
    private static int numberOfActors;

    public static List<Relation> convert(RelationMatrix relationMatrix, List<Relation> currentRelationList) {
        matrix = relationMatrix.getMatrix();
        numberOfActors = relationMatrix.getNumberOfActors();

        List<Relation> relations = new ArrayList<>();
        for (int i = 0; i < numberOfActors; i++) {
            for (int j = 0; j < i; j++) {
                if (existsRelation(currentRelationList, i + 1, j + 1)) {
                    relations.add(getRelation(i, j));
                }
            }
        }
        return relations;
    }

    private static boolean existsRelation(List<Relation> currentRelationList, int firstActorId, int secondActorId) {
        Relation relation = new Relation(new Actor(firstActorId), new Actor(secondActorId));
        return currentRelationList.contains(relation);
    }

    private static Relation getRelation(int firstActorId, int secondActorId) {
        Actor firstActor = new Actor(firstActorId + 1);
        Actor secondActor = new Actor(secondActorId + 1);
        if (matrix[firstActorId][secondActorId] == 0) {
            return new Relation(firstActor, secondActor, RelationType.NONE);
        }
        if (matrix[firstActorId][secondActorId] == -1) {
            return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
        }
        return new Relation(firstActor, secondActor, RelationType.POSITIVE);
    }
}
