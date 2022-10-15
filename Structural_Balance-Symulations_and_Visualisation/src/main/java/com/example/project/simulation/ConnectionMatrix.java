package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.List;

public class ConnectionMatrix {
    private static final int RELATION_EXISTENCE_ANNOTATION = 1;

    private final List<Relation> relationList;
    private final int numberOfActors;
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
        matrix[firstActorId - 1][secondActorId - 1] = RELATION_EXISTENCE_ANNOTATION;
        matrix[secondActorId - 1][firstActorId - 1] = RELATION_EXISTENCE_ANNOTATION;
    }

    public int get(int firstActorId, int secondActorId) {
        return matrix[firstActorId - 1][secondActorId - 1];
    }

    public boolean existsRelation(int firstActorId, int secondActorId) {
        return matrix[firstActorId - 1][secondActorId - 1] == RELATION_EXISTENCE_ANNOTATION ||
                matrix[secondActorId - 1][firstActorId - 1] == RELATION_EXISTENCE_ANNOTATION;
    }

    public int getNumberOfExistingRelations() {
        int sum = 0;
        for (int i = 0; i < numberOfActors; i++) {
            for (int j = 0; j < i; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    int[][] getMatrix() {
        return matrix;
    }

    public int getNumberOfActors() {
        return numberOfActors;
    }
}
