package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.List;

public class RelationMatrix {
    private final List<Relation> relationList;
    private final int numberOfActors;
    private int[][] matrix;

    public RelationMatrix(List<Relation> relationList, int numberOfActors) {
        this.relationList = relationList;
        this.numberOfActors = numberOfActors;
        createRelationMatrix();
    }

    private void createRelationMatrix() {
        matrix = new int[numberOfActors][numberOfActors];
        for (Relation relation : relationList) {
            setRelationInMatrix(relation);
        }
    }

    private void setRelationInMatrix(Relation relation) {
        int firstActorId = relation.getFirstActor().getActorId();
        int secondActorId = relation.getSecondActor().getActorId();
        if (isPositive(relation)) {
            setMatrixValue(firstActorId, secondActorId, RelationType.POSITIVE.getRelationAnnotation());
        }
        if (isNegative(relation)) {
            setMatrixValue(firstActorId, secondActorId, RelationType.NEGATIVE.getRelationAnnotation());
        }
    }

    private boolean isNegative(Relation relation) {
        return relation.getRelationType().equals(RelationType.NEGATIVE);
    }

    private boolean isPositive(Relation relation) {
        return relation.getRelationType().equals(RelationType.POSITIVE);
    }

    private void setMatrixValue(int firstActorId, int secondActorId, int relationAnnotation) {
        matrix[firstActorId - 1][secondActorId - 1] = relationAnnotation;
        matrix[secondActorId - 1][firstActorId - 1] = relationAnnotation;
    }

    public int get(int firstActorId, int secondActorId) {
        return matrix[firstActorId - 1][secondActorId - 1];
    }

    public void set(int firstActorId, int secondActorId, int value) {
        matrix[firstActorId - 1][secondActorId - 1] = value;
        matrix[secondActorId - 1][firstActorId - 1] = value;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getNumberOfActors() {
        return numberOfActors;
    }
}
