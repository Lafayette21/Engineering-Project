package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.List;

public class ConnectionMatrix {
    private List<Relation> relationList;
    private int numberOfActors;
    private int[][] matrix;

    public ConnectionMatrix(List<Relation> relationList, int numberOfActors) {
        this.relationList = relationList;
        this.numberOfActors = numberOfActors;
        createConnectionMatrix();
    }

    private void createConnectionMatrix() {
        matrix = new int[numberOfActors][numberOfActors];
        for (Relation relation : relationList) {
            if (existsRelation(relation)) {
                setRelationInMatrix(relation);
            }
        }
    }

    private boolean existsRelation(Relation relation) {
        return !relation.getRelationType().equals(RelationType.NONE);
    }

    private void setRelationInMatrix(Relation relation) {
        int firstActorId = relation.getFirstActor().getActorId();
        int secondActorId = relation.getSecondActor().getActorId();
        matrix[firstActorId - 1][secondActorId - 1] = 1;
        matrix[secondActorId - 1][firstActorId - 1] = 1;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
