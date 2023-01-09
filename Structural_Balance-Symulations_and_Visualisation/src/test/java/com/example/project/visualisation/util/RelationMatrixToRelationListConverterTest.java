package com.example.project.visualisation.util;

import com.example.project.simulation.RelationMatrix;
import com.example.project.simulation.SampleRelationListFactory;
import com.example.project.util.RelationMatrixToRelationListConverter;
import com.example.project.visualisation.model.Relation;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationMatrixToRelationListConverterTest {
    private static final int NUMBER_OF_ACTORS = 6;

    @Test
    public void shouldConvertRelationMatrixToRelationList() {
        List<Relation> sampleRelationList = SampleRelationListFactory.create();
        RelationMatrix relationMatrix = new RelationMatrix(sampleRelationList, NUMBER_OF_ACTORS);

        List<Relation> actualRelationList = RelationMatrixToRelationListConverter.convert(relationMatrix, sampleRelationList);

        assertThat(actualRelationList).containsAll(sampleRelationList);
    }

}