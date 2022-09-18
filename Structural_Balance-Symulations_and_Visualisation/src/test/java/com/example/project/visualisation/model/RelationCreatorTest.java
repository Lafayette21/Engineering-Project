package com.example.project.visualisation.model;

import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationCreatorTest {
    private static final int NUMBER_OF_ACTORS = 4;
    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int CONNECTION_CREATION_PERCENTAGE = 100;
    private static final int POS_TO_NEG_PERCENTAGE = 50;

    private RelationCreator relationCreator;

    @Test
    public void shouldCreateRelations() {
        List<Actor> actorList = IntStream.range(1, NUMBER_OF_ACTORS + 1)
                .mapToObj(Actor::new)
                .collect(Collectors.toList());
        ActorsParametersValues actorsParametersValues =
                new ActorsParametersValues(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        ConnectionsParametersValues connectionsParametersValues =
                new ConnectionsParametersValues(CONNECTION_CREATION_PERCENTAGE, POS_TO_NEG_PERCENTAGE);

        relationCreator = new RelationCreator(actorsParametersValues, connectionsParametersValues, actorList);
        Set<Relation> relationList = relationCreator.createRelations();

        assertThat(relationList).hasSize(6);
    }
}