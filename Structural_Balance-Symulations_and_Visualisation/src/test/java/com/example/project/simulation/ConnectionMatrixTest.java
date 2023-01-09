package com.example.project.simulation;


import com.example.project.visualisation.model.Relation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionMatrixTest {
    private static final int NUMBER_OF_ACTORS = 6;
    private static final int EXISTENCE_ANNOTATION = 1;
    private static final int NON_EXISTENCE_ANNOTATION = 0;

    @Test
    public void shouldCreateConnectionMatrix() {
        List<Relation> relationList = SampleRelationListFactory.create();
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, NUMBER_OF_ACTORS);

        int[][] expectedMatrix = getExpectedMatrix();
        int[][] actualMatrix = connectionMatrix.getMatrix();

        assertThat(actualMatrix).isEqualTo(expectedMatrix);
    }
    @Test
    public void shouldGetNumberOfExistingRelations(){
        List<Relation> relationList = SampleRelationListFactory.create();
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(relationList, NUMBER_OF_ACTORS);

        int expectedNumberOfRelations = 6;
        int actualNumberOfRelations = connectionMatrix.getNumberOfExistingRelations();

        assertThat(actualNumberOfRelations).isEqualTo(expectedNumberOfRelations);
    }

    private int[][] getExpectedMatrix() {
        int[][] matrix = new int[NUMBER_OF_ACTORS][NUMBER_OF_ACTORS];
        setConnection(matrix, 1, 2, EXISTENCE_ANNOTATION);
        setConnection(matrix, 2, 3, NON_EXISTENCE_ANNOTATION);
        setConnection(matrix, 4, 5, EXISTENCE_ANNOTATION);
        setConnection(matrix, 5, 6, EXISTENCE_ANNOTATION);
        setConnection(matrix, 2, 4, NON_EXISTENCE_ANNOTATION);
        setConnection(matrix, 3, 5, EXISTENCE_ANNOTATION);
        setConnection(matrix, 1, 4, EXISTENCE_ANNOTATION);
        setConnection(matrix, 2, 5, NON_EXISTENCE_ANNOTATION);
        setConnection(matrix, 3, 6, EXISTENCE_ANNOTATION);
        return matrix;
    }

    private void setConnection(int[][] expectedMatrix, int firstActorId, int secondActorId, int relationExistenceAnnotation) {
        expectedMatrix[firstActorId - 1][secondActorId - 1] = relationExistenceAnnotation;
        expectedMatrix[secondActorId - 1][firstActorId - 1] = relationExistenceAnnotation;
    }


}