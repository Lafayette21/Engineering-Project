package com.example.project.visualisation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class ConnectionMatrix {
    private static final int UPPER_BOUND_FOR_RANDOM_GENERATOR = 100;
    private final int rowNumber;
    private final int columnNumber;
    private final int[][] matrix;
    private final int numberOfActors;

    public ConnectionMatrix(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.numberOfActors = rowNumber * columnNumber;
        matrix = new int[numberOfActors][numberOfActors];
    }

    public void createConnections(int connectionCreationPercentage) {
        Map<Integer,Integer> actorsRelations = new HashMap<>();
        IntStream.range(0,numberOfActors).map()

    }

    List<Integer> getNeighboursOfActor()

    private void setMatrixElement(int verticalPosition, int horizontalPosition, int connectionCreationPercentage) {
        int randomNumber = new Random().nextInt(UPPER_BOUND_FOR_RANDOM_GENERATOR);
        if (randomNumber < connectionCreationPercentage &&
                isNeighbour(verticalPosition + 1, horizontalPosition + 1)) {
            matrix[verticalPosition][horizontalPosition] = 1;
            matrix[horizontalPosition][verticalPosition] = 1;
        }
    }

    private boolean isNeighbour(int vertical, int horizontal) {
        return isNeighbourInTheSameRow(vertical, horizontal);
    }

    private boolean isNeighbourInTheSameRow(int vertical, int horizontal) {
        return vertical == horizontal + 1 || vertical == horizontal - 1;
    }
}
