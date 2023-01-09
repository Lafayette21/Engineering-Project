package com.example.project.visualisation.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationTest {
    @Test
    public void shouldReturnTrueWhenRelationIsNeighbouring(){
        Relation relation1 = new Relation(new Actor(1), new Actor(2));
        Relation relation2 = new Relation(new Actor(1), new Actor(3));

        assertThat(relation1.isNeighbouringRelation(relation2)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenRelationsTheSameOrNotNeighbouring(){
        Relation relation1 = new Relation(new Actor(1), new Actor(2));
        Relation relation2 = new Relation(new Actor(1), new Actor(2));
        Relation relation3 = new Relation(new Actor(3), new Actor(4));

        assertThat(relation1.isNeighbouringRelation(relation2)).isFalse();
        assertThat(relation1.isNeighbouringRelation(relation3)).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenRelationAreEqual(){
      Relation relation1 = new Relation(new Actor(1), new Actor(2));
      Relation relation2 = new Relation(new Actor(2), new Actor(1));

      assertThat(relation1).isEqualTo(relation2);
    }
}
