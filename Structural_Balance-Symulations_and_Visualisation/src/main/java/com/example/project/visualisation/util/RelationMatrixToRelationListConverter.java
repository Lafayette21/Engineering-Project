package com.example.project.visualisation.util;

import com.example.project.simulation.RelationMatrix;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.ArrayList;
import java.util.List;

public class RelationMatrixToRelationListConverter {
    private static int[][] matrix;

    public static List<Relation> convert(RelationMatrix relationMatrix, List<Relation> comparationRelationList) {
        matrix = relationMatrix.getMatrix();
        int numberOfActors = relationMatrix.getNumberOfActors();

        List<Relation> relations = new ArrayList<>();
        for (int i = 0; i < numberOfActors; i++) {
            for (int j = 0; j < i; j++) {
                if (existsRelation(comparationRelationList, i + 1, j + 1)) {
                    relations.add(getRelation(i + 1, j + 1));
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
        Actor firstActor = new Actor(firstActorId);
        Actor secondActor = new Actor(secondActorId);
        if (matrix[firstActorId - 1][secondActorId - 1] == 0) {
            return new Relation(firstActor, secondActor, RelationType.NONE);
        }
        if (matrix[firstActorId - 1][secondActorId - 1] == -1) {
            return new Relation(firstActor, secondActor, RelationType.NEGATIVE);
        }
        return new Relation(firstActor, secondActor, RelationType.POSITIVE);
    }
}
