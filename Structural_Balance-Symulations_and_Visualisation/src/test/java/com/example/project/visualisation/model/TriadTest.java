package com.example.project.visualisation.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TriadTest {
    @Test
    public void shouldGetTriadTypeForTriad(){
        Relation relation1 = createRelation(RelationType.POSITIVE);
        Relation relation2 = createRelation(RelationType.NEGATIVE);
        Relation relation3 = createRelation(RelationType.POSITIVE);
        Triad triad = new Triad(relation1, relation2, relation3);

        TriadType actualTriadType = triad.getTypeLevel();

        assertThat(actualTriadType).isEqualTo(TriadType.HALF_NEGATIVE);
    }

    @Test
    public void shouldGetNoneTriadTypeForTriad(){
        Relation relation1 = createRelation(RelationType.POSITIVE);
        Relation relation2 = createRelation(RelationType.NONE);
        Relation relation3 = createRelation(RelationType.POSITIVE);
        Triad triad = new Triad(relation1, relation2, relation3);

        TriadType actualTriadType = triad.getTypeLevel();

        assertThat(actualTriadType).isEqualTo(TriadType.NONE);
    }

    private Relation createRelation(RelationType relationType) {
        Relation relation = mock(Relation.class);
        when(relation.getRelationType()).thenReturn(relationType);
        return relation;
    }
}