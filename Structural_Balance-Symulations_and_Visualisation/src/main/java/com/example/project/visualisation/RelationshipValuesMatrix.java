package com.example.project.visualisation;

public class RelationshipValuesMatrix {
    private final ConnectionMatrix connectionMatrix;
    private int[][] matrix;

    public RelationshipValuesMatrix(ConnectionMatrix connectionMatrix) {
        this.connectionMatrix = connectionMatrix;
        matrix = createMatrix(connectionMatrix.getNumberOfActors());
    }

    private int[][] createMatrix(int numberOfActors) {
        matrix = new int[numberOfActors][numberOfActors];
        return matrix;
    }
}
