package com.example.project.visualisation.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelationTest {
    @Test
    public void shouldReturnTrueWhenRelationContainsActor(){
        Relation relation = new Relation(new Actor(1), new Actor(2));
        Actor actor = new Actor(1);

        assertThat(relation.containsActor(actor)).isTrue();
    }
}