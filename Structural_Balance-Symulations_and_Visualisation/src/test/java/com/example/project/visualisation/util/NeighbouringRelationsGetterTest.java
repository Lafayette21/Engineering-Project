package com.example.project.visualisation.util;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NeighbouringRelationsGetterTest {
    @Test
    public void shouldReturnListOfNeighbouringRelations() {
        Relation relation1 = new Relation(new Actor(1), new Actor(2));
        Relation relation2 = new Relation(new Actor(1), new Actor(3));
        Relation relation3 = new Relation(new Actor(2), new Actor(3));
        Relation relation4 = new Relation(new Actor(3), new Actor(4));
        List<Relation> relationList = List.of(relation1, relation2, relation3, relation4);

        List<Relation> actualRelationNeighbours = NeighbouringRelationsGetter.get(relation1, relationList);
        List<Relation> expectedRelationNeighbours = List.of(relation2, relation3);

        assertThat(actualRelationNeighbours).isEqualTo(expectedRelationNeighbours);
    }
}