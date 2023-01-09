package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationMatrixTest {
    private static final int POSITIVE_RELATION_ANNOTATION = 1;
    private static final int NEGATIVE_RELATION_ANNOTATION = -1;
    private static final int NON_EXISTENT_RELATION_ANNOTATION = 0;

    private static final int NUMBER_OF_ACTORS = 6;

    @Test
    public void shouldCreateRelationMatrix() {
        List<Relation> relationList = SampleRelationListFactory.create();
        RelationMatrix relationMatrix = new RelationMatrix(relationList, NUMBER_OF_ACTORS);

        int[][] expectedMatrix = getExpectedMatrix();
        int[][] actualMatrix = relationMatrix.getMatrix();

        assertThat(actualMatrix).isEqualTo(expectedMatrix);
    }

    private int[][] getExpectedMatrix() {
        int[][] matrix = new int[NUMBER_OF_ACTORS][NUMBER_OF_ACTORS];
        setRelation(matrix, 1, 2, NEGATIVE_RELATION_ANNOTATION);
        setRelation(matrix, 2, 3, NON_EXISTENT_RELATION_ANNOTATION);
        setRelation(matrix, 4, 5, POSITIVE_RELATION_ANNOTATION);
        setRelation(matrix, 5, 6, NEGATIVE_RELATION_ANNOTATION);
        setRelation(matrix, 2, 4, NON_EXISTENT_RELATION_ANNOTATION);
        setRelation(matrix, 3, 5, POSITIVE_RELATION_ANNOTATION);
        setRelation(matrix, 1, 4, POSITIVE_RELATION_ANNOTATION);
        setRelation(matrix, 2, 5, NON_EXISTENT_RELATION_ANNOTATION);
        setRelation(matrix, 3, 6, NEGATIVE_RELATION_ANNOTATION);
        return matrix;
    }

    private void setRelation(int[][] expectedMatrix, int firstActorId, int secondActorId, int relationExistenceAnnotation) {
        expectedMatrix[firstActorId - 1][secondActorId - 1] = relationExistenceAnnotation;
        expectedMatrix[secondActorId - 1][firstActorId - 1] = relationExistenceAnnotation;
    }
}