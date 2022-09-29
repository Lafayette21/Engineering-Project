package com.example.project.simulation;


import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionMatrixTest {
    private static final int NUMBER_OF_ACTORS = 6;
    private static final int EXISTENCE = 1;
    private static final int NO_EXISTENCE = 0;


    @Test
    public void shouldCreateConnectionMatrix() {
        List<Relation> relationList = SampleRelationListFactory.create();
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, NUMBER_OF_ACTORS);

        int[][] expectedMatrix = getExpectedMatrix();
        int[][] actualMatrix = connectionMatrix.getMatrix();

        assertThat(actualMatrix).isEqualTo(expectedMatrix);
    }

    private int[][] getExpectedMatrix() {
        int[][] matrix = new int[NUMBER_OF_ACTORS][NUMBER_OF_ACTORS];
        setConnection(matrix, 1, 2, EXISTENCE);
        setConnection(matrix, 2, 3, NO_EXISTENCE);
        setConnection(matrix, 4, 5, EXISTENCE);
        setConnection(matrix, 5, 6, EXISTENCE);
        setConnection(matrix, 2, 4, NO_EXISTENCE);
        setConnection(matrix, 3, 5, EXISTENCE);
        setConnection(matrix, 1, 4, EXISTENCE);
        setConnection(matrix, 2, 5, NO_EXISTENCE);
        setConnection(matrix, 3, 6, EXISTENCE);
        setConnection(matrix, 1, 5, EXISTENCE);
        setConnection(matrix, 2, 6, NO_EXISTENCE);
        return matrix;
    }

    private void setConnection(int[][] expectedMatrix, int firstActorId, int secondActorId, int relationExistenceAnnotation) {
        expectedMatrix[firstActorId - 1][secondActorId - 1] = relationExistenceAnnotation;
        expectedMatrix[secondActorId - 1][firstActorId - 1] = relationExistenceAnnotation;
    }


}