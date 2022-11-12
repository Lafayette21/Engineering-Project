package com.example.project.visualisation.util;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.Triad;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TriadFactoryTest {
    @Test
    public void shouldCreateTriads(){
        Relation relation1 = createRelationWithActors(1, 2);
        Relation relation2 = createRelationWithActors(1, 3);
        Relation relation3 = createRelationWithActors(2, 3);
        List<Relation> relationList = List.of(relation1, relation2, relation3);

        Set<Triad> actualTriadSet = TriadFactory.createTriads(relationList);
        Set<Triad> expectedTriad = Set.of(new Triad(relation1, relation2, relation3));

        assertThat(actualTriadSet).isEqualTo(expectedTriad);
    }

    private Relation createRelationWithActors(int firstActorId, int secondActorId) {
        return new Relation(new Actor(firstActorId), new Actor(secondActorId));
    }

}