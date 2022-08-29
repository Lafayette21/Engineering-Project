package com.example.project.visualisation;

import java.util.Random;

public class ConnectionMatrix {
    private static final int UPPER_BOUND_FOR_RANDOM_GENERATOR = 100;
    private final int numberOfActors;
    private final int[][] matrix;

    public ConnectionMatrix(int numberOfActors) {
        this.numberOfActors = numberOfActors;
        matrix = new int[numberOfActors][numberOfActors];
    }

    public int getNumberOfActors() {
        return numberOfActors;
    }

    public void createConnections(int connectionCreationPercentage) {
        for (int vertical = 0; vertical < numberOfActors; vertical++) {
            for (int horizontal = 0; horizontal < vertical; horizontal++) {
                if (!isDiagonal(vertical, horizontal)) {
                    setMatrixElement(vertical, horizontal, connectionCreationPercentage);
                }
            }
        }
    }

    private boolean isDiagonal(int vertical, int horizontal) {
        return vertical == horizontal;
    }

    private void setMatrixElement(int verticalPosition, int horizontalPosition, int connectionCreationPercentage) {
        int randomNumber = new Random().nextInt(UPPER_BOUND_FOR_RANDOM_GENERATOR);
        if (randomNumber < connectionCreationPercentage) {
            matrix[verticalPosition][horizontalPosition] = 1;
            matrix[horizontalPosition][verticalPosition] = 1;
        }
    }
}
